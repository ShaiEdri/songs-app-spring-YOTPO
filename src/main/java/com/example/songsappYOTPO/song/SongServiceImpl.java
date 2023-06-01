package com.example.songsappYOTPO.song;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findByLengthGreaterThan(Double length) {
        return songRepository.findByLengthGreaterThan(length);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
