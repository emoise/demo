package com.yonder.demo.service.csv;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class MockCsvSaver implements CsvSaver {
  @Override
  public void save(String csvBody) {
    //intentionally empty
  }
}
