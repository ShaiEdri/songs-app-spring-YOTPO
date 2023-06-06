package com.example.songsappYOTPO.producer;

import com.example.songsappYOTPO.shared.Person;
import com.example.songsappYOTPO.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producer extends Person {
    @OneToMany
    @JoinColumn(name = "producer_id") //instead of table, try without it and see
    @Builder.Default
    private List<Song> songs = new ArrayList<>();

    @Override
    public String toString() {
        return "Producer{" +
                super.toString() +
                "songs=" + songs +
                '}';
    }
}
