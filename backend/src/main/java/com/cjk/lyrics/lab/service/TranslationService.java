package com.cjk.lyrics.lab.service;

import com.cjk.lyrics.lab.dto.OpenAIRequest;
import com.cjk.lyrics.lab.dto.OpenAIResponse;
import com.cjk.lyrics.lab.model.Track;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class TranslationService {
    
    @Value("${openai.api.key:}")
    private String openaiApiKey;
    
    @Value("${openai.api.url:https://api.openai.com/v1}")
    private String openaiApiUrl;
    
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public TranslationService() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.objectMapper = new ObjectMapper();
    }
    
    @PostConstruct
    public void init() {
        // Load API key from environment variable if not set via properties
        if (openaiApiKey == null || openaiApiKey.isEmpty()) {
            openaiApiKey = System.getenv("OPENAI_API_KEY");
            log.info("Loaded OpenAI API key from environment variable: {}", 
                openaiApiKey != null ? openaiApiKey.substring(0, Math.min(20, openaiApiKey.length())) + "..." : "null");
        }
    }
    
    public String translateLyrics(String lyrics, Track.Language language) {
        if (openaiApiKey == null || openaiApiKey.isEmpty()) {
            log.warn("OpenAI API key not configured, returning mock translation");
            return generateMockTranslation(lyrics, language);
        }
        
        try {
            return translateWithOpenAI(lyrics, language);
        } catch (Exception e) {
            log.error("Error translating lyrics with OpenAI API", e);
            return generateFallbackTranslation(lyrics, language);
        }
    }
    
    private String translateWithOpenAI(String lyrics, Track.Language language) {
        String languageName = getLanguageName(language);
        String prompt = buildTranslationPrompt(lyrics, languageName);

        OpenAIRequest request = new OpenAIRequest();
        OpenAIRequest.Message systemMessage = new OpenAIRequest.Message();
        systemMessage.setRole("system");
        systemMessage.setContent("You are a professional translator specializing in CJK (Chinese, Japanese, Korean) languages. Provide accurate, natural translations that preserve the emotional and cultural context of song lyrics.");

        OpenAIRequest.Message userMessage = new OpenAIRequest.Message();
        userMessage.setRole("user");
        userMessage.setContent(prompt);

        request.setMessages(List.of(systemMessage, userMessage));

        try {
            String response = webClient.post()
                    .uri(openaiApiUrl + "/chat/completions")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                    .body(Mono.just(request), OpenAIRequest.class)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> {
                        log.error("OpenAI API returned error status: {}", clientResponse.statusCode());
                        return Mono.error(new RuntimeException("OpenAI API error: " + clientResponse.statusCode()));
                    })
                    .bodyToMono(String.class)
                    .block();

            return parseOpenAIResponse(response);
        } catch (Exception e) {
            log.error("Error calling OpenAI API", e);
            throw new RuntimeException("Failed to call OpenAI API: " + e.getMessage(), e);
        }
    }
    
    private String parseOpenAIResponse(String responseJson) {
        try {
            OpenAIResponse response = objectMapper.readValue(responseJson, OpenAIResponse.class);
            if (response.getChoices() != null && !response.getChoices().isEmpty()) {
                return response.getChoices().get(0).getMessage().getContent().trim();
            }
        } catch (Exception e) {
            log.error("Error parsing OpenAI response", e);
        }
        return "Translation failed - unable to parse response";
    }
    
    private String buildTranslationPrompt(String lyrics, String languageName) {
        return String.format("""
            Please translate the following %s song lyrics to English. 
            Maintain the poetic structure and emotional tone of the original.
            If there are cultural references or idioms, provide a natural English equivalent.
            
            Lyrics to translate:
            %s
            
            Translation:
            """, languageName, lyrics);
    }
    
    private String getLanguageName(Track.Language language) {
        return switch (language) {
            case JA -> "Japanese";
            case KO -> "Korean";
            case ZH -> "Chinese";
        };
    }
    
    private String generateMockTranslation(String lyrics, Track.Language language) {
        String languageName = getLanguageName(language);
        return String.format("""
            [Translation placeholder for %s lyrics]
            
            Configure OPENAI_API_KEY environment variable to enable automatic translation.
            
            Original lyrics:
            %s
            """, languageName, lyrics);
    }
    
    private String generateFallbackTranslation(String lyrics, Track.Language language) {
        String languageName = getLanguageName(language);
        return String.format("""
            [Translation temporarily unavailable]
            
            OpenAI API quota exceeded. Please add credits to your OpenAI account at https://platform.openai.com/account/billing
            
            Language: %s
            Original lyrics: %s
            """, languageName, lyrics);
    }
}
