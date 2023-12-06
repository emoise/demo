package com.yonder.demo.service;

import com.yonder.demo.dto.in.CityWeatherInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "getWeather",
    url = "${weather-app.external-weather-api}")
public interface ExternalApiWeatherProvider {

  @GetMapping(value = "/{city}")
  CityWeatherInput getWeatherForCity(@RequestParam("city") String city);
}
