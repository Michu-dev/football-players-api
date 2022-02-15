package com.example.demo;

import com.example.demo.player.Player;
import com.example.demo.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }


    public void addNewPlayer(Player player) {
        Optional<Player> playerOptional = playerRepository
                .findPlayerByPesel(player.getPesel());
        if (playerOptional.isPresent()) {
            throw new IllegalStateException("Pesel taken");
        }
        playerRepository.insert(player);
    }

    public void deletePlayer(String pesel) {
        boolean exists = playerRepository.existsPlayerByPesel(pesel);
        if (!exists) {
            throw new IllegalStateException(
                    "student with pesel " + pesel + " doesn't exist");
        }
        playerRepository.deletePlayerByPesel(pesel);
    }


    public void updatePlayer(String pesel,
                                 String name,
                                 String city,
                                 String stadiumName,
                                 Integer capacity,
                                 String hasLightning,
                                 String hasUnderSoilHeating) {
        Optional<Player> optionalPlayer = playerRepository.findPlayerByPesel(pesel);
//                .orElseThrow(() -> new IllegalStateException(
//                     "player with pesel " + pesel + " doesn't exist"));

        if (!optionalPlayer.isPresent()) {
            throw new IllegalStateException("player with pesel " + pesel + " doesn't exist");
        }

        Player player = optionalPlayer.get();


        System.out.println(player + " found and modified");

        Team newTeam = new Team();
        Stadium newStadium = new Stadium();

        if (name.length() > 0) {
            newTeam.setName(name);
        }

        if (city.length() > 0) {
            newTeam.setCity(city);
        }

        if (stadiumName.length() > 0) {
            newStadium.setName(stadiumName);

            if (capacity != null) {
                newStadium.setCapacity(capacity);
            }

            if (hasLightning != null && (hasLightning.toLowerCase().contains("yes") || hasLightning.toLowerCase().contains("no"))) {
                newStadium.setHasLightning(hasLightning);
            }

            if (hasUnderSoilHeating != null && (hasUnderSoilHeating.toLowerCase().contains("yes") || hasUnderSoilHeating.toLowerCase().contains("no"))) {
                newStadium.setHasUnderSoilHeating(hasUnderSoilHeating);
            }

            newTeam.setStadium(newStadium);

        }

        if (name != null && city != null && stadiumName != null) {
            player.setTeam(newTeam);
        }

        playerRepository.save(player);



    }
}
