package com.yonder.demo.service.csv;

import com.yonder.demo.dto.out.CityWeatherOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvMapper {

  public String map(List<CityWeatherOutput> cityWeatherOutputs) {
    var csv = "Name, temperature, wind" + System.lineSeparator();
    csv +=
        cityWeatherOutputs.stream()
            .map(it -> it.name() + "," + it.temperature() + "," + it.wind())
            .collect(Collectors.joining(System.lineSeparator()));
    return csv;
  }
}
