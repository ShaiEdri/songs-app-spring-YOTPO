package com.example.songsappYOTPO.producer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producer> getProducers() {
        return producerRepository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id){
        producerRepository.deleteById(id);
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public List<Producer> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {
        return producerRepository.findByLastNameAndFirstNameAllIgnoreCase(
                lastName, firstName);
    }

    @Override
    public int updateProducer(Long id, Producer producer) {
        return producerRepository.updateProducer(id, producer);
    }
}
