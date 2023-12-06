package com.yonder.demo.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherResultOutput(
    @JsonProperty("result") List<CityWeatherOutput> cityWeatherOutputEntries) {}
