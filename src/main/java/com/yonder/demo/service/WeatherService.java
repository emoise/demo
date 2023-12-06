package com.yonder.demo.service;

import com.yonder.demo.dto.out.WeatherResultOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

  private final WeatherCache weatherCache;

  private final WeatherInputToOutputMapper mapper;

  public WeatherResultOutput getWeatherForCities(List<String> cities) {

    var cityWeatherInputMap =
        weatherCache.getCache().entrySet().stream()
            .filter(it -> cities.contains(it.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    return mapper.map(cityWeatherInputMap);
  }
}
