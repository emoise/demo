package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "weather-app.external-weather-enabled", havingValue = "true")
public class ExternalWeatherRetrievalService implements WeatherRetrievalService {

  private final ExternalApiWeatherProvider externalApiWeatherProvider;

  public Map<String, CityWeatherInput> getWeather(List<String> cities) {
    var cityWeatherInputMap = new HashMap<String, CityWeatherInput>();
    cities.forEach(city -> cityWeatherInputMap.put(city, getWeatherInputForCity(city)));
    return cityWeatherInputMap;
  }

  private CityWeatherInput getWeatherInputForCity(String city) {
    CityWeatherInput cityWeatherInput;
    try {
      cityWeatherInput = externalApiWeatherProvider.getWeatherForCity(city);
    } catch (FeignException e) {
      cityWeatherInput = new CityWeatherInput(0f, 0f, "no data", Collections.emptyList());
    }
    return cityWeatherInput;
  }
}
