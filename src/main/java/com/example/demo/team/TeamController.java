package com.example.demo.team;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> fetchAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> registerNewTeam(@RequestBody Team team) {
        Team newTeam = teamService.addNewTeam(team);
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{teamName}")
    public ResponseEntity<?> deleteTeam(@PathVariable("teamName") String name) {
        teamService.deleteTeam(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{teamName}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable("teamName") String name,
            @RequestParam(required = false) String stadiumName) {
        Team team = teamService.updateTeam(name, stadiumName);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

}
