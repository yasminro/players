package com.assessment.players.model.dto;

import com.assessment.players.model.Player;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerDto {
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
    @JsonProperty("throws")
    private String throwingHand;
    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;

    public static PlayerDto of(Player player) {
        return PlayerDto.builder()
                .playerID(player.getPlayerID())
                .birthYear(player.getBirthYear())
                .birthMonth(player.getBirthMonth())
                .birthDay(player.getBirthDay())
                .birthCountry(player.getBirthCountry())
                .birthState(player.getBirthState())
                .birthCity(player.getBirthCity())
                .deathYear(player.getDeathYear())
                .deathMonth(player.getDeathMonth())
                .deathDay(player.getDeathDay())
                .deathCountry(player.getDeathCountry())
                .deathState(player.getDeathState())
                .deathCity(player.getDeathCity())
                .nameFirst(player.getNameFirst())
                .nameLast(player.getNameLast())
                .nameGiven(player.getNameGiven())
                .weight(player.getWeight())
                .height(player.getHeight())
                .bats(player.getBats())
                .throwingHand(player.getThrowingHand())
                .debut(player.getDebut())
                .finalGame(player.getFinalGame())
                .retroID(player.getRetroID())
                .bbrefID(player.getBbrefID())
                .build();
    }
}