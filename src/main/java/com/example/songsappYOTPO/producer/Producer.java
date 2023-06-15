package com.example.songsappYOTPO.producer;

import com.example.songsappYOTPO.shared.Person;
import com.example.songsappYOTPO.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    //songs prop
    @OneToMany
    @JoinColumn(name = "producer_id") //instead of table, try without it and see
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @NotNull
    private List<Song> songs = new ArrayList<>();

    @Override
    public String toString() {
        return "Producer{" +
                super.toString() +
                "songs=" + songs +
                '}';
    }


}
