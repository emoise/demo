package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import com.yonder.demo.dto.in.ForecastInput;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherInputToOutputMapperTests {

  private WeatherInputToOutputMapper weatherInputToOutputMapper = new WeatherInputToOutputMapper();

  @Test
  void map_whenEmptyInput_emptyResultIsReturned() {
    // given

    // when
    var actualWeatherResultOutput = weatherInputToOutputMapper.map(Collections.emptyMap());

    // then
    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries()).isEmpty();
  }

  @Test
  void map_whenValidInput_outputWithAveragesReturned() {
    // given
    var citiesWithWeather = new HashMap<String, CityWeatherInput>();
    var clujForecastInputDay1 = new ForecastInput(1, 1f, 2f);
    var clujForecastInputDay2 = new ForecastInput(2, 3f, 4f);
    var clujForecastInputList = List.of(clujForecastInputDay1, clujForecastInputDay2);
    citiesWithWeather.put(
        "Cluj-Napoca", new CityWeatherInput(1f, 1f, "desc", clujForecastInputList));

    var bucurestiForecastInputDay1 = new ForecastInput(1, 5f, 6f);
    var bucurestiForecastInputDay2 = new ForecastInput(2, 7f, 8f);
    var bucurestiForecastInputList =
        List.of(bucurestiForecastInputDay1, bucurestiForecastInputDay2);
    citiesWithWeather.put(
        "Bucuresti", new CityWeatherInput(1f, 1f, "desc", bucurestiForecastInputList));

    // when
    var actualWeatherResultOutput = weatherInputToOutputMapper.map(citiesWithWeather);

    // then
    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries()).hasSize(2);

    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries().get(0).name())
        .isEqualTo("Bucuresti");
    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries().get(0).wind()).isEqualTo(5f);
    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries().get(0).temperature())
        .isEqualTo(4.3333335f);

    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries().get(1).name())
        .isEqualTo("Cluj-Napoca");
    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries().get(1).wind())
        .isEqualTo(2.3333333f);
    assertThat(actualWeatherResultOutput.cityWeatherOutputEntries().get(1).temperature())
        .isEqualTo(1.6666666f);
  }
}
