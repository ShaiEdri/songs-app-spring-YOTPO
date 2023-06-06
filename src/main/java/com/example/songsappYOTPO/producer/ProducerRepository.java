package com.example.songsappYOTPO.producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    List<Producer> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

    @Transactional
    @Modifying
    @Query("UPDATE Producer p SET p.firstName = :#{#producer.firstName}, " +
            "p.lastName = :#{#producer.lastName} WHERE p.id = ?1")
    /**doc
     When using a modifying query method,
     Spring Data JPA automatically returns the number of affected rows as
     the result of the method execution
     @Modifying will require a transaction
     */
    int updateProducer(Long id, @Param("producer") Producer producer);

}
