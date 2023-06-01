package com.example.songsappYOTPO.singer;

import java.util.List;

public interface SingerService {
    List<Singer> getSingers();
    void deleteById(Long id);
    List<Singer> findByLastName(String lastName);
}
