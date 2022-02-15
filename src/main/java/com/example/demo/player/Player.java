package com.example.demo.player;

import com.example.demo.Team;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
public class Player {
    @Id
    private String id;

    @Indexed(unique = true)
    private String pesel;

    private String firstName;
    private String lastName;
    private int salary;
    private Date dateOfBirth;
    private String role;
    private String position;
    private Team team;

    public Player(String pesel, String firstName, String lastName, int salary, Date dateOfBirth, String role, String position, Team team) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.position = position;
        this.team = team;
    }
}
