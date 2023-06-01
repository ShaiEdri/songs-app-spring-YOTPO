package com.example.songsappYOTPO.shared;

import com.example.songsappYOTPO.producer.Producer;
import com.example.songsappYOTPO.producer.ProducerRepository;
import com.example.songsappYOTPO.singer.Singer;
import com.example.songsappYOTPO.singer.SingerRepository;
import com.example.songsappYOTPO.song.Song;
import com.example.songsappYOTPO.song.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoadData implements CommandLineRunner {
    private final SingerRepository singerRepository;
    private final SongRepository songRepository;
    private final ProducerRepository producerRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        saveData();
    }
    private void saveData() {

        Producer producer1 = new Producer();
        producer1.setFirstName("Nile");
        producer1.setLastName("Rogers");

        //Singer singer1 = new Singer();
        Singer singer1 = Singer.builder()
                .firstName("Bob")
                .lastName("Dylan").build();

        Singer singer2 = Singer.builder()
                .firstName("Bob")
                .lastName("Marley").build();

        Song song1 = Song.builder()
                .name("the dylan song")
                .length(2.3).build();

        song1.getSingers().add(singer1);
        song1.setProducer(producer1);
        singer1.getSongs().add(song1);
        producer1.getSongs().add(song1);

        singerRepository.save(singer1);
        songRepository.save(song1);
        producerRepository.save(producer1);

        Song song2 = Song.builder()
                .name("Big brown eyes")
                .length(2.3).build();

        song2.getSingers().add(singer2);
        singer2.getSongs().add(song2);
        singerRepository.save(singer2);
        songRepository.save(song2);
    }
}
