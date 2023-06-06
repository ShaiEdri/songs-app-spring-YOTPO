package com.example.songsappYOTPO.singer;

import com.example.songsappYOTPO.shared.Person;
import com.example.songsappYOTPO.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Singer extends Person {

    //songs prop
    @ManyToMany(mappedBy = "singers")
    @Builder.Default
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Song> songs = new ArrayList<>();


//    public Singer(Long id, String firstName, String lastName) {
//        super(id, firstName, lastName);
//    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


}
