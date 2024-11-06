package com.assessment.players.loader;

import com.assessment.players.model.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
@Slf4j
public class CsvLoader implements Loader<Player> {

    public List<Player> loadData(String fileName) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            log.error("File not found: {}", fileName);
            throw new RuntimeException("File not found: " + fileName);
        }

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder<Player>(reader)
                    .withType(Player.class)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            log.error("Error loading CSV file: {}", e.getMessage());
            throw new RuntimeException("Error parsing CSV", e);
        }
    }
}