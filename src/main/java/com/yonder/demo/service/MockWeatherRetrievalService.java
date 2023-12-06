package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import com.yonder.demo.dto.in.ForecastInput;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(value = "weather-app.external-weather-enabled", havingValue = "false")
public class MockWeatherRetrievalService implements WeatherRetrievalService {

  public Map<String, CityWeatherInput> getWeather(List<String> cities) {
    var externalServiceResponse = new HashMap<String, CityWeatherInput>();
    var forecastInputList = new ArrayList<ForecastInput>();
    forecastInputList.add(new ForecastInput(1, 1f, 2f));
    externalServiceResponse.put(
        "Cluj-Napoca", new CityWeatherInput(3f, 4f, "Cluj", forecastInputList));
    externalServiceResponse.put("Arad", new CityWeatherInput(-1.5f, 7f, "Arad", forecastInputList));
    externalServiceResponse.put(
        "Bucuresti", new CityWeatherInput(8f, 2.5f, "Buc", forecastInputList));

    return externalServiceResponse;
  }
}
