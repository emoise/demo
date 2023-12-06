package com.yonder.demo.dto.in;

import java.util.List;

public record CityWeatherInput(
    Float temperature, Float wind, String description, List<ForecastInput> forecast) {}
