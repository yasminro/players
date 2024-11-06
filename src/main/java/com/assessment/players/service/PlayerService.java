package com.assessment.players.service;

import com.assessment.players.model.dto.PlayerDto;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<PlayerDto> getAllPlayers(int page, int size);
    Optional<PlayerDto> getPlayerById(String id);
}