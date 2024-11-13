package com.assessment.players.controller;

import com.assessment.players.model.dto.PlayerDto;
import com.assessment.players.service.PlayerService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PlayerDto>> getAllPlayers(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                         @RequestParam(defaultValue = "50") @Min(1) int size) {
        log.info("Fetching all players with pagination: page: {}, size: {}", page, size);

        List<PlayerDto> players = playerService.getAllPlayers(page, size);
        if (players.isEmpty()) {
            log.info("No players found");
            return ResponseEntity.noContent().build();
        }

        log.info("Fetched {} players", players.size());
        return ResponseEntity.ok(players);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable String id) {
        log.info("Fetching player with id: {}", id);
        PlayerDto player = playerService.getPlayerById(id).orElseThrow();
        log.info("Found player: {}", player);
        return ResponseEntity.ok(player);
    }
}