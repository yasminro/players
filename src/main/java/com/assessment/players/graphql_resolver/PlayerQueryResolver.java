package com.assessment.players.graphql_resolver;

import com.assessment.players.model.dto.PlayerDto;
import com.assessment.players.service.PlayerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PlayerQueryResolver implements GraphQLQueryResolver {
    private final PlayerService playerService;

    public List<PlayerDto> getAllPlayers(int page, int size) {
        return playerService.getAllPlayers(page, size);
    }

    public PlayerDto getPlayerById(String id) {
        Optional<PlayerDto> playerOptional = playerService.getPlayerById(id);
        return playerOptional.orElse(null);
    }
}