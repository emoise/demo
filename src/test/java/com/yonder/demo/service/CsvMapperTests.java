package com.yonder.demo.service;

import com.yonder.demo.dto.out.CityWeatherOutput;
import com.yonder.demo.service.csv.CsvMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CsvMapperTests {

  private CsvMapper csvMapper = new CsvMapper();

  @Test
  void map_whenValidInput_csvGenerated() {
    // given
    var cityWeatherOutputs = new ArrayList<CityWeatherOutput>();
    cityWeatherOutputs.add(new CityWeatherOutput("Cluj-Napoca", 1f, 2f));
    cityWeatherOutputs.add(new CityWeatherOutput("Bucuresti", 3f, 4.5f));

    // when
    var actualCsv = csvMapper.map(cityWeatherOutputs);

    // then
    assertThat(actualCsv)
        .isEqualToIgnoringWhitespace(
            """
        Name, temperature, wind
        Cluj-Napoca,1.0,2.0
        Bucuresti,3.0,4.5""");
  }

  @Test
  void map_whenEmptyInput_onlyHeadersCsvGenerated() {
    // given
    var cityWeatherOutputs = new ArrayList<CityWeatherOutput>();

    // when
    var actualCsv = csvMapper.map(cityWeatherOutputs);

    // then
    assertThat(actualCsv)
        .isEqualToIgnoringWhitespace("""
                Name, temperature, wind""");
  }
}
