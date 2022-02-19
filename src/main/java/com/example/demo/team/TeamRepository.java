package com.example.demo.team;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, String> {
    Optional<Team> findTeamByName(String name);
    Boolean existsTeamByName(String name);
    void deleteTeamByName(String name);
}
