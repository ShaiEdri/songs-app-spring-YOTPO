package com.example.songsappYOTPO.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProducerRepositoryTest {
    @Autowired
    private ProducerRepository producerRepository;
    Producer testedProducer;
    private final String FIRST_NAME = "Israel";
    private final String LAST_NAME = "Israeli";
    @BeforeEach
    void setUp() {
        testedProducer = Producer.builder()
                .firstName(FIRST_NAME).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastnameAndFirstnameAllIgnoreCaseTestLower() {
        producerRepository.save(testedProducer);
        List<Producer> producersFound = producerRepository.findByLastNameAndFirstNameAllIgnoreCase(
                LAST_NAME.toLowerCase(),
                FIRST_NAME.toLowerCase()
        );
        assertTrue(producersFound.size() > 0);
        assertEquals(producersFound.get(0).getLastName(), LAST_NAME);
    }

    @Test
    void findByLastnameAndFirstnameAllIgnoreCaseTestUpper() {
        producerRepository.save(testedProducer);
        List<Producer> producersFound = producerRepository.findByLastNameAndFirstNameAllIgnoreCase(
                LAST_NAME.toUpperCase(),
                FIRST_NAME.toUpperCase());
        assertTrue(producersFound.size() > 0);
        assertEquals(producersFound.get(0).getLastName(), LAST_NAME);
    }

}