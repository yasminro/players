package com.assessment.players.service;

import com.assessment.players.exception.PlayerNotFoundException;
import com.assessment.players.model.Player;
import com.assessment.players.model.dto.PlayerDto;
import com.assessment.players.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    @Override
    public List<PlayerDto> getAllPlayers(int page, int size) {
        log.info("Retrieving all players: page: {}, size: {}", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Player> players = playerRepository.findAll(pageRequest).getContent();
        log.info("Total players retrieved: {}", players.size());
        return players.stream().map(PlayerDto::of).toList();
    }

    @Override
    public PlayerDto getPlayerById(String id) {
        log.info("Retrieving player with id: {}",id);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        return PlayerDto.of(player);
    }
}