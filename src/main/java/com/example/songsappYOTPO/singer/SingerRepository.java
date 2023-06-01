package com.example.songsappYOTPO.singer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SingerRepository extends JpaRepository<Singer,Long> {
    List<Singer> findByLastName(String lastName);
}
