package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherUpdaterService {

  private final WeatherCache weatherCache;
  private final WeatherRetrievalService weatherRetrievalService;

  public void triggerUpdate(List<String> cities) {
    Map<String, CityWeatherInput> externalCityWeather = weatherRetrievalService.getWeather(cities);
    weatherCache.update(externalCityWeather);
  }
}
