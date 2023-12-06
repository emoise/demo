package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;

import java.util.List;
import java.util.Map;

public interface WeatherRetrievalService {
  Map<String, CityWeatherInput> getWeather(List<String> cities);
}
