package com.yonder.demo.service.csv;

import com.yonder.demo.dto.out.CityWeatherOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvFileGenerator {

  private final CsvMapper csvMapper;

  private final CsvSaver csvSaver;

  @Async
  public void save(List<CityWeatherOutput> cityWeatherOutputs) {
    csvSaver.save(csvMapper.map(cityWeatherOutputs));
  }
}
