package com.example.songsappYOTPO.shared;

import com.example.songsappYOTPO.singer.Singer;
import com.example.songsappYOTPO.singer.SingerRepository;
import com.example.songsappYOTPO.song.Song;
import com.example.songsappYOTPO.song.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {
    private final SingerRepository singerRepository;
    private final SongRepository songRepository;

    public LoadData(SingerRepository singerRepository, SongRepository songRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        saveData();
    }
    private void saveData() {


        Singer singer1 = new Singer();
        singer1.setLastName("Bob");
        singer1.setFirstName("Dylan");


        Singer singer2 = new Singer();
        singer2.setFirstName("bob");
        singer2.setLastName("Marley");


        // song1
        Song song1 = new Song();
        song1.setName("the dylan song");
        song1.setLength(2.3);

        song1.getSingers().add(singer1);

        singer1.getSongs().add(song1);

        singerRepository.save(singer1);
        songRepository.save(song1);
        // song1

        Song song2 = new Song();
        song2.setName("Big brown eyes");
        song2.setLength(2.3);
        song2.getSingers().add(singer2);
        singer2.getSongs().add(song2);
        singerRepository.save(singer2);
        songRepository.save(song2);
    }
}
