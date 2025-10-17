package com.cjk.lyrics.lab.service;

import com.cjk.lyrics.lab.dto.AnkiExportDto;
import com.cjk.lyrics.lab.dto.TrackDto;
import com.cjk.lyrics.lab.model.Track;
import com.cjk.lyrics.lab.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackService {
    
    private final TrackRepository trackRepository;
    
    public List<TrackDto> getAllTracks() {
        return trackRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public Optional<TrackDto> getTrackById(String id) {
        return trackRepository.findById(id)
                .map(this::convertToDto);
    }
    
    public TrackDto createTrack(TrackDto trackDto) {
        Track track = convertToEntity(trackDto);
        track.setCreatedAt(LocalDateTime.now());
        Track savedTrack = trackRepository.save(track);
        return convertToDto(savedTrack);
    }
    
    public Optional<TrackDto> updateTrack(String id, TrackDto trackDto) {
        return trackRepository.findById(id)
                .map(existingTrack -> {
                    existingTrack.setTitle(trackDto.getTitle());
                    existingTrack.setArtist(trackDto.getArtist());
                    existingTrack.setAlbum(trackDto.getAlbum());
                    existingTrack.setOriginalLyrics(trackDto.getOriginalLyrics());
                    existingTrack.setTranslatedLyrics(trackDto.getTranslatedLyrics());
                    existingTrack.setLanguage(trackDto.getLanguage());
                    existingTrack.setDifficulty(trackDto.getDifficulty());
                    existingTrack.setTags(trackDto.getTags());
                    Track updatedTrack = trackRepository.save(existingTrack);
                    return convertToDto(updatedTrack);
                });
    }
    
    public boolean deleteTrack(String id) {
        if (trackRepository.existsById(id)) {
            trackRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private TrackDto convertToDto(Track track) {
        TrackDto dto = new TrackDto();
        dto.setId(track.getId());
        dto.setTitle(track.getTitle());
        dto.setArtist(track.getArtist());
        dto.setAlbum(track.getAlbum());
        dto.setOriginalLyrics(track.getOriginalLyrics());
        dto.setTranslatedLyrics(track.getTranslatedLyrics());
        dto.setLanguage(track.getLanguage());
        dto.setDifficulty(track.getDifficulty());
        dto.setTags(track.getTags());
        dto.setCreatedAt(track.getCreatedAt());
        dto.setLastStudied(track.getLastStudied());
        return dto;
    }
    
    private Track convertToEntity(TrackDto dto) {
        Track track = new Track();
        track.setId(dto.getId());
        track.setTitle(dto.getTitle());
        track.setArtist(dto.getArtist());
        track.setAlbum(dto.getAlbum());
        track.setOriginalLyrics(dto.getOriginalLyrics());
        track.setTranslatedLyrics(dto.getTranslatedLyrics());
        track.setLanguage(dto.getLanguage());
        track.setDifficulty(dto.getDifficulty());
        track.setTags(dto.getTags());
        track.setCreatedAt(dto.getCreatedAt());
        track.setLastStudied(dto.getLastStudied());
        return track;
    }
}
