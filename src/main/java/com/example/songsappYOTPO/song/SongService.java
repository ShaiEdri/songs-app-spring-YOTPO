package com.example.songsappYOTPO.song;

import java.util.List;

public interface SongService {
    List<Song> getSongs();
    List<Song>findByLengthGreaterThan(Double length);
    void deleteById(Long id);
}
