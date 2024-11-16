package com.assessment.players.service;

import com.assessment.players.model.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getAllPlayers(int page, int size);
    PlayerDto getPlayerById(String id);
}