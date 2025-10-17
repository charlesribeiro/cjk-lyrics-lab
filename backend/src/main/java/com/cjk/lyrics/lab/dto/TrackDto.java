package com.cjk.lyrics.lab.dto;

import com.cjk.lyrics.lab.model.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private String id;
    private String title;
    private String artist;
    private String album;
    private String originalLyrics;
    private String translatedLyrics;
    private Track.Language language;
    private String difficulty;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime lastStudied;
}
