package com.example.demo.stadium;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private int capacity;
    private String hasLightning;
    private String hasUnderSoilHeating;


    public Stadium(String name, int capacity, String hasLightning, String hasUnderSoilHeating) {
        this.name = name;
        this.capacity = capacity;
        this.hasLightning = hasLightning;
        this.hasUnderSoilHeating = hasUnderSoilHeating;
    }
}
