package com.assessment.players.loader;

import com.assessment.players.model.Player;
import com.assessment.players.repository.PlayerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {
    private final PlayerRepository playerRepository;
    private final LoaderFactory loaderFactory;

    @Value("${csv.file-name}")
    private String fileName;

    @PostConstruct
    public void init() {
        log.info("Initializing data...");

        long playerCount = playerRepository.count();
        log.info("Player count in the database: {}", playerCount);

        if (playerCount == 0) {
            log.info("No players found in the database. Loading data from CSV...");
            loadDataToDb();
        } else {
            log.info("Players already exist in the database. Skipping data load.");
        }
    }

    private void loadDataToDb() {
        List<Player> players;
        try {
            String suffix = getSuffix(fileName);
            log.info("Loading player data from {} file: {}",suffix, fileName);
            Loader<Player> loader = loaderFactory.getLoader(suffix);
            players = loader.loadData(fileName);
            log.info("Successfully loaded {} players from CSV.", players.size());
        } catch (Exception e) {
            log.error("Error loading player data from CSV: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load player data from CSV", e);
        }

        if (!players.isEmpty()) {
            log.info("Saving {} players to the database...", players.size());
            playerRepository.saveAll(players);
            log.info("Successfully saved {} players to the database.", players.size());
        } else {
            log.warn("No players to save to the database after loading from CSV.");
        }
    }

    private static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }
}