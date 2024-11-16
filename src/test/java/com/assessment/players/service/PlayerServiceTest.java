package com.assessment.players.service;

import com.assessment.players.exception.PlayerNotFoundException;
import com.assessment.players.model.Player;
import com.assessment.players.model.dto.PlayerDto;
import com.assessment.players.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlayers_ShouldReturnListOfPlayers_WhenPlayersExist() {
        Player player = new Player();
        player.setPlayerID("123");
        player.setNameFirst("John");
        player.setNameLast("Doe");
        player.setBirthYear(1990);
        player.setThrowingHand("Right");

        Page<Player> page = new PageImpl<>(List.of(player));
        when(playerRepository.findAll(PageRequest.of(0, 10))).thenReturn(page);

        List<PlayerDto> players = playerService.getAllPlayers(0, 10);

        assertThat(players).hasSize(1);
        assertThat(players.get(0).getPlayerID()).isEqualTo("123");
        assertThat(players.get(0).getNameFirst()).isEqualTo("John");
        assertThat(players.get(0).getNameLast()).isEqualTo("Doe");
        verify(playerRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    void getAllPlayers_ShouldReturnEmptyList_WhenNoPlayersExist() {
        Page<Player> emptyPage = new PageImpl<>(Collections.emptyList());
        when(playerRepository.findAll(PageRequest.of(0, 10))).thenReturn(emptyPage);

        List<PlayerDto> players = playerService.getAllPlayers(0, 10);

        assertThat(players).isEmpty();
        verify(playerRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    void getPlayerById_ShouldReturnPlayer_WhenPlayerExists() {
        Player player = new Player();
        player.setPlayerID("123");
        player.setNameFirst("John");
        player.setNameLast("Doe");
        player.setBirthYear(1990);
        player.setThrowingHand("Right");

        when(playerRepository.findById("123")).thenReturn(Optional.of(player));

        PlayerDto playerDto = playerService.getPlayerById("123");

        assertThat(playerDto).isNotNull();
        assertThat(playerDto.getPlayerID()).isEqualTo("123");
        assertThat(playerDto.getNameFirst()).isEqualTo("John");
        assertThat(playerDto.getNameLast()).isEqualTo("Doe");
        verify(playerRepository, times(1)).findById("123");
    }

    @Test
    void getPlayerById_ShouldThrowException_WhenPlayerDoesNotExist() {
        when(playerRepository.findById("123")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.getPlayerById("123"))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessage("Player not found with id: 123");

        verify(playerRepository, times(1)).findById("123");
    }
}