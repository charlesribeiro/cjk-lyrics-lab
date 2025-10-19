package com.cjk.lyrics.lab.service;

import com.cjk.lyrics.lab.dto.AnkiCardDto;
import com.cjk.lyrics.lab.dto.AnkiExportDto;
import com.cjk.lyrics.lab.model.Track;
import com.cjk.lyrics.lab.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnkiExportService {
    
    private final TrackRepository trackRepository;
    
    public Optional<AnkiExportDto> generateAnkiExport(String trackId) {
        return trackRepository.findById(trackId)
                .map(this::createAnkiExport);
    }
    
    public String convertToCsv(AnkiExportDto exportDto) {
        StringBuilder csv = new StringBuilder();
        for (AnkiCardDto card : exportDto.getCards()) {
            csv.append(escapeCsv(card.getFront())).append(",");
            csv.append(escapeCsv(card.getBack())).append(",");
            csv.append(escapeCsv(String.join(" ", card.getTags()))).append(",");
            csv.append(escapeCsv(card.getExample() != null ? card.getExample() : ""));
            csv.append("\n");
        }
        return csv.toString();
    }
    
    private AnkiExportDto createAnkiExport(Track track) {
        List<AnkiCardDto> cards = new ArrayList<>();
        
        // Create a basic card with the full lyrics
        AnkiCardDto lyricsCard = new AnkiCardDto();
        lyricsCard.setFront(track.getTitle() + " - " + track.getArtist());
        lyricsCard.setBack(track.getOriginalLyrics());
        lyricsCard.setTags(Arrays.asList(
                track.getLanguage().toString(),
                track.getDifficulty() != null ? track.getDifficulty() : "unknown"
        ));
        cards.add(lyricsCard);
        
        // Add translation card if available
        if (track.getTranslatedLyrics() != null && !track.getTranslatedLyrics().isEmpty()) {
            AnkiCardDto translationCard = new AnkiCardDto();
            translationCard.setFront(track.getOriginalLyrics());
            translationCard.setBack(track.getTranslatedLyrics());
            translationCard.setTags(Arrays.asList(
                    track.getLanguage().toString(),
                    "translation"
            ));
            cards.add(translationCard);
        }
        
        AnkiExportDto exportDto = new AnkiExportDto();
        exportDto.setTrackId(track.getId());
        exportDto.setCards(cards);
        
        return exportDto;
    }
    
    private String escapeCsv(String value) {
        if (value == null) {
            return "\"\"";
        }
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
