package com.example.demo.stadium;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stadiums")
@AllArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    public ResponseEntity<List<Stadium>> fetchAllStadiums() {
        List<Stadium> stadiums = stadiumService.getAllStadiums();
        return new ResponseEntity<>(stadiums, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Stadium> registerNewStadium(@RequestBody Stadium stadium) {
        Stadium newStadium = stadiumService.addNewStadium(stadium);
        return new ResponseEntity<>(newStadium, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{stadiumName}")
    public ResponseEntity<?> deleteStadium(@PathVariable("stadiumName") String name) {
        stadiumService.deleteStadium(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{stadiumName}")
    public ResponseEntity<Stadium> updateTeam(
            @PathVariable("stadiumName") String name,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String hasLightning,
            @RequestParam(required = false) String hasUnderSoilHeating) {
        Stadium stadium = stadiumService.updateStadium(name, capacity, hasLightning, hasUnderSoilHeating);
        return new ResponseEntity<>(stadium, HttpStatus.OK);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

}
