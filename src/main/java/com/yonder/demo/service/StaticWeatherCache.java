package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StaticWeatherCache implements WeatherCache {

  private static Map<String, CityWeatherInput> cache = new HashMap<>();

  @Override
  public void update(Map<String, CityWeatherInput> input) {

    for (Map.Entry<String, CityWeatherInput> entry : input.entrySet()) {
      cache.put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public Map<String, CityWeatherInput> getCache() {
    return cache;
  }
}
