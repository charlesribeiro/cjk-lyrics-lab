package com.cjk.lyrics.lab.repository;

import com.cjk.lyrics.lab.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, String> {
    List<Track> findByLanguage(Track.Language language);
    List<Track> findByArtistContainingIgnoreCase(String artist);
}
