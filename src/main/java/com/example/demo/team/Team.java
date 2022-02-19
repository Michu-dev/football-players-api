package com.example.demo.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String city;
    private String stadium;

    public Team(String name, String city, String stadium) {
        this.name = name;
        this.city = city;
        this.stadium = stadium;
    }
}
