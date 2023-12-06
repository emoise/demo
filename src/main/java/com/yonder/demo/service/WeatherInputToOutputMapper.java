package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import com.yonder.demo.dto.in.ForecastInput;
import com.yonder.demo.dto.out.CityWeatherOutput;
import com.yonder.demo.dto.out.WeatherResultOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

@Service
public class WeatherInputToOutputMapper {

  public WeatherResultOutput map(Map<String, CityWeatherInput> input) {

    var cityWeatherOutputEntries = new ArrayList<CityWeatherOutput>();
    for (Map.Entry<String, CityWeatherInput> entry : input.entrySet()) {
      Float temperature = input.get(entry.getKey()).temperature();
      Float wind = entry.getValue().wind();
      for (ForecastInput forecastInput : entry.getValue().forecast()) {
        temperature += forecastInput.temperature();
        wind += forecastInput.wind();
      }
      var size = 1 + entry.getValue().forecast().size();
      var citWeatherOutput = new CityWeatherOutput(entry.getKey(), temperature / size, wind / size);
      cityWeatherOutputEntries.add(citWeatherOutput);
    }
    Collections.sort(cityWeatherOutputEntries, Comparator.comparing(CityWeatherOutput::name));
    return new WeatherResultOutput(cityWeatherOutputEntries);
  }
}
