package com.example.demo.player;

import com.example.demo.player.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findPlayerByPesel(String pesel);
    Boolean existsPlayerByPesel(String pesel);
    void deletePlayerByPesel(String pesel);
}
