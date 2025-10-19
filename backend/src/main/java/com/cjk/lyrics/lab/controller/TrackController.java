package com.cjk.lyrics.lab.controller;

import com.cjk.lyrics.lab.dto.AnkiExportDto;
import com.cjk.lyrics.lab.dto.TrackDto;
import com.cjk.lyrics.lab.service.AnkiExportService;
import com.cjk.lyrics.lab.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TrackController {
    
    private final TrackService trackService;
    private final AnkiExportService ankiExportService;
    
    @GetMapping
    public ResponseEntity<List<TrackDto>> getAllTracks() {
        return ResponseEntity.ok(trackService.getAllTracks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getTrack(@PathVariable String id) {
        return trackService.getTrackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<TrackDto> createTrack(@RequestBody TrackDto trackDto) {
        TrackDto createdTrack = trackService.createTrack(trackDto);
        return ResponseEntity.ok(createdTrack);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TrackDto> updateTrack(@PathVariable String id, @RequestBody TrackDto trackDto) {
        return trackService.updateTrack(id, trackDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable String id) {
        boolean deleted = trackService.deleteTrack(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/anki-export")
    public ResponseEntity<String> exportToAnki(@PathVariable String id) {
        return ankiExportService.generateAnkiExport(id)
                .map(exportDto -> {
                    String csv = ankiExportService.convertToCsv(exportDto);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.TEXT_PLAIN);
                    headers.setContentDispositionFormData("attachment", "anki-export-" + id + ".csv");
                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(csv);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
