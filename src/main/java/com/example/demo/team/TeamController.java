package com.example.demo.team;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<Team> fetchAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public void registerNewTeam(@RequestBody Team team) {
        teamService.addNewTeam(team);
    }

    @DeleteMapping(path = "{teamName}")
    public void deleteTeam(@PathVariable("teamName") String name) {
        teamService.deleteTeam(name);
    }

    @PutMapping(path = "{teamName}")
    public void updateTeam(
            @PathVariable("teamName") String name,
            @RequestParam(required = false) String stadiumName) {
        teamService.updateTeam(name, stadiumName);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

}
