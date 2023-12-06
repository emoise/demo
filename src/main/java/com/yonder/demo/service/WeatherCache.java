package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;

import java.util.Map;

public interface WeatherCache {

  void update(Map<String, CityWeatherInput> input);

  Map<String, CityWeatherInput> getCache();
}
