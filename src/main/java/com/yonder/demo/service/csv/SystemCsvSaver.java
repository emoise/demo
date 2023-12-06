package com.yonder.demo.service.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@Profile("!test")
public class SystemCsvSaver implements CsvSaver {

  Logger logger = LoggerFactory.getLogger(SystemCsvSaver.class);

  public void save(String csvBody) {
    Path filePath = Path.of("weather.csv");
    try {
      Files.writeString(
          filePath, csvBody, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
      logger.info("CSF file saved under {}", filePath.toAbsolutePath());
    } catch (IOException e) {
      logger.error("Error saving CSV content under {}", filePath.toAbsolutePath(), e);
    }
  }
}
