package com.example.demo.stadium;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }


    public Stadium addNewStadium(Stadium stadium) {
        Optional<Stadium> stadiumOptional = stadiumRepository
                .findStadiumByName(stadium.getName());
        if (stadiumOptional.isPresent()) {
            throw new IllegalStateException("Name taken");
        }
        return stadiumRepository.insert(stadium);
    }

    public void deleteStadium(String name) {
        boolean exists = stadiumRepository.existsStadiumByName(name);
        if (!exists) {
            throw new IllegalStateException(
                    "stadium with name " + name + " doesn't exist");
        }
        stadiumRepository.deleteStadiumByName(name);
    }


    public Stadium updateStadium(String name,
                              Integer capacity,
                              String hasLightning,
                              String hasUnderSoilHeating) {
        Optional<Stadium> optionalStadium = stadiumRepository.findStadiumByName(name);

        if (!optionalStadium.isPresent()) {
            throw new IllegalStateException("stadium with name " + name + " doesn't exist");
        }

        Stadium stadium = optionalStadium.get();
        System.out.println(stadium + " found and modified");


        if (capacity != null && !Objects.equals(stadium.getCapacity(), capacity)) {
            stadium.setCapacity(capacity);
        }

        if (hasLightning.toLowerCase().contains("yes") || hasLightning.toLowerCase().contains("no")) {
            stadium.setHasLightning(hasLightning);
        }

        if (hasUnderSoilHeating.toLowerCase().contains("yes") || hasUnderSoilHeating.toLowerCase().contains("no")) {
            stadium.setHasUnderSoilHeating(hasUnderSoilHeating);
        }

        return stadiumRepository.save(stadium);

    }

}
