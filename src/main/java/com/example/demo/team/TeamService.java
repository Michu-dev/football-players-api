package com.example.demo.team;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }


    public void addNewTeam(Team team) {
        Optional<Team> teamOptional = teamRepository
                .findTeamByName(team.getName());
        if (teamOptional.isPresent()) {
            throw new IllegalStateException("Name taken");
        }
        teamRepository.insert(team);
    }

    public void deleteTeam(String name) {
        boolean exists = teamRepository.existsTeamByName(name);
        if (!exists) {
            throw new IllegalStateException(
                    "team with name " + name + " doesn't exist");
        }
        teamRepository.deleteTeamByName(name);
    }


    public void updateTeam(String name,
                             String newStadium) {
        Optional<Team> optionalTeam = teamRepository.findTeamByName(name);

        if (!optionalTeam.isPresent()) {
            throw new IllegalStateException("team with name " + name + " doesn't exist");
        }

        Team team = optionalTeam.get();
        System.out.println(team + " found and modified");


        if (newStadium != null && newStadium.length() > 0 && !Objects.equals(team.getStadium(), newStadium)) {
            team.setStadium(newStadium);
        }

        teamRepository.save(team);

    }

}
