package com.yonder.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityValidator {

  private final List<String> validCities;

  public CityValidator(@Value("${weather-app.valid.cities}") List<String> validCities) {
    this.validCities = validCities;
  }

  public List<String> filter(List<String> cities) {
    return cities.stream().filter(validCities::contains).toList();
  }
}
