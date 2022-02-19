package com.example.demo.stadium;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StadiumRepository extends MongoRepository<Stadium, String> {
    Optional<Stadium> findStadiumByName(String name);
    Boolean existsStadiumByName(String name);
    void deleteStadiumByName(String name);
}
