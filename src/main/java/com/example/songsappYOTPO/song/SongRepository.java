package com.example.songsappYOTPO.song;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song>findByLengthGreaterThan(Double length);
}
