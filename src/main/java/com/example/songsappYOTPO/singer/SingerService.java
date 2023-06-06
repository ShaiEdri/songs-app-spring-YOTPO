package com.example.songsappYOTPO.singer;

import java.util.List;

public interface SingerService {
    List<Singer> getSingers();
    List<Singer> saveSingers(List<Singer> singers);
    void deleteById(Long id);
    List<Singer> findByLastName(String lastName);
}
