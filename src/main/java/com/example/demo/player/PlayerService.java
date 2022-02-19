package com.example.demo.player;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                             String newTeam,
                             Integer newSalary) {
        Optional<Player> optionalPlayer = playerRepository.findPlayerByPesel(pesel);
//                .orElseThrow(() -> new IllegalStateException(
//                     "player with pesel " + pesel + " doesn't exist"));

        if (!optionalPlayer.isPresent()) {
            throw new IllegalStateException("player with pesel " + pesel + " doesn't exist");
        }

        Player player = optionalPlayer.get();
        System.out.println(player + " found and modified");


        if (newTeam != null && newTeam.length() > 0 && !Objects.equals(player.getTeam(), newTeam)) {
            player.setTeam(newTeam);
        }

        if (newSalary != null && !Objects.equals(player.getSalary(), newSalary)) {
            player.setSalary(newSalary);
        }

        playerRepository.save(player);

    }

}
