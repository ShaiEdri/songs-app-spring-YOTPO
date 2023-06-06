package com.example.songsappYOTPO.singer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    private final SingerRepository singerRepository;

    public SingerServiceImpl(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
    public List<Singer> getSingers() {
        return singerRepository.findAll();
    }

    @Override
    public List<Singer> saveSingers(List<Singer> singers) {
        return singerRepository.saveAll(singers);
    }

    @Override
    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public List<Singer> findByLastName(String lastName) {
        return singerRepository.findByLastName(lastName);
    }
}
