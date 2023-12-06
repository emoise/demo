package com.yonder.demo.controller;

import com.yonder.demo.dto.out.WeatherResultOutput;
import com.yonder.demo.service.CityValidator;
import com.yonder.demo.service.WeatherService;
import com.yonder.demo.service.WeatherUpdaterService;
import com.yonder.demo.service.csv.CsvFileGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController implements WeatherControllerApi {

  private final WeatherService weatherService;

  private final WeatherUpdaterService weatherUpdaterService;

  private final CityValidator cityValidator;

  private final CsvFileGenerator csvFileGenerator;

  Logger logger = LoggerFactory.getLogger(WeatherController.class);

  @Override
  @GetMapping("/weather")
  public ResponseEntity<WeatherResultOutput> getWeather(@RequestParam List<String> cities) {

    logger.info("Receiving request for cities + {}", cities);

    List<String> validCities = cityValidator.filter(cities);

    weatherUpdaterService.triggerUpdate(validCities);

    var weatherResult = weatherService.getWeatherForCities(validCities);

    csvFileGenerator.save(weatherResult.cityWeatherOutputEntries());

    return new ResponseEntity<>(weatherResult, HttpStatus.OK);
  }
}
