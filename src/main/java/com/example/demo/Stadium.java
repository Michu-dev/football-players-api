package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {
    private String name;
    private int capacity;
    private String hasLightning;
    private String hasUnderSoilHeating;
}
