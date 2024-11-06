package com.assessment.players.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "players")
@Data
public class Player {
    @Id
    private String playerID;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private String bats;
    @Column(name = "throws")
    private String throwingHand;
    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;
}