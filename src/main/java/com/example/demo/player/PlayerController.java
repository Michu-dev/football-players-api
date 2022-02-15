package com.example.demo.player;

import com.example.demo.PlayerService;
import com.example.demo.player.Player;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<Player> fetchAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    public void registerNewPlayer(@RequestBody Player player) {
        playerService.addNewPlayer(player);
    }

    @DeleteMapping(path = "{playerPesel}")
    public void deleteStudent(@PathVariable("playerPesel") String pesel) {
        playerService.deletePlayer(pesel);
    }

    @PutMapping(path = "{playerPesel}")
    public void updatePlayer(
            @PathVariable("playerPesel") String pesel,
            @RequestParam String name,
            @RequestParam String city,
            @RequestParam String stadiumName,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String hasLightning,
            @RequestParam(required = false) String hasUnderSoilHeating) {
        playerService.updatePlayer(pesel, name, city, stadiumName, capacity, hasLightning, hasUnderSoilHeating);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

}
