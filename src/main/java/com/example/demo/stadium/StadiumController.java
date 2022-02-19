package com.example.demo.stadium;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stadiums")
@AllArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    public List<Stadium> fetchAllStadiums() {
        return stadiumService.getAllStadiums();
    }

    @PostMapping
    public void registerNewStadium(@RequestBody Stadium stadium) {
        stadiumService.addNewStadium(stadium);
    }

    @DeleteMapping(path = "{stadiumName}")
    public void deleteStadium(@PathVariable("stadiumName") String name) {
        stadiumService.deleteStadium(name);
    }

    @PutMapping(path = "{stadiumName}")
    public void updateTeam(
            @PathVariable("stadiumName") String name,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String hasLightning,
            @RequestParam(required = false) String hasUnderSoilHeating) {
        stadiumService.updateStadium(name, capacity, hasLightning, hasUnderSoilHeating);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

}
