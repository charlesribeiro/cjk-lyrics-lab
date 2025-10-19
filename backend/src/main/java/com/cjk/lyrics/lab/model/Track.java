package com.cjk.lyrics.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String title;
    private String artist;
    private String album;
    
    @Column(columnDefinition = "TEXT")
    private String originalLyrics;
    
    @Column(columnDefinition = "TEXT")
    private String translatedLyrics;
    
    @Enumerated(EnumType.STRING)
    private Language language;
    
    private String difficulty;
    
    @ElementCollection
    private List<String> tags;
    
    private LocalDateTime createdAt;
    private LocalDateTime lastStudied;
    
    public enum Language {
        JA, KO, ZH
    }
}
