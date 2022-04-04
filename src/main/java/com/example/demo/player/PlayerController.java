package com.example.demo.player;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> fetchAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> registerNewPlayer(@RequestBody Player player) {
        Player newPlayer = playerService.addNewPlayer(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{playerPesel}")
    public ResponseEntity<?> deleteStudent(@PathVariable("playerPesel") String pesel) {
        playerService.deletePlayer(pesel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{playerPesel}")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable("playerPesel") String pesel,
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) Integer salary) {
        Player player = playerService.updatePlayer(pesel, teamName, salary);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

}
