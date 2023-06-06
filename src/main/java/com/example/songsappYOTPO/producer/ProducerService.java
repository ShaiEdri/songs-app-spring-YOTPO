package com.example.songsappYOTPO.producer;

import java.util.List;
import java.util.Optional;

public interface ProducerService {
    List<Producer> getProducers();
    Optional<Producer> findById(Long id);
    void deleteById(Long id );
    Producer save(Producer producer);
    List<Producer> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
    int updateProducer(Long id, Producer producer);
}
