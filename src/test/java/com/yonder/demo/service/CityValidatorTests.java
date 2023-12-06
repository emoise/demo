package com.yonder.demo.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CityValidatorTests {

  private List<String> validCities =
      List.of("Cluj-Napoca", "Bucuresti", "Timisoara", "Constanta", "Baia-Mare", "Arad");

  @Test
  void filter_whenEmptyValidCitiesAndEmptyInput_emptyListReturned() {
    // given
    List<String> emptyList = Collections.emptyList();

    CityValidator cityValidator = new CityValidator(emptyList);

    // when
    var actualCities = cityValidator.filter(emptyList);

    // then
    assertThat(actualCities).isEmpty();
  }

  @Test
  void filter_whenValidCitiesAndEmptyInput_emptyListReturned() {
    // given
    CityValidator cityValidator = new CityValidator(validCities);

    List<String> emptyList = Collections.emptyList();

    // when
    var actualCities = cityValidator.filter(emptyList);

    // then
    assertThat(actualCities).isEmpty();
  }

  @Test
  void filter_whenValidCitiesAndInputListIsTheSame_samleListReturned() {
    // given
    var cityValidator = new CityValidator(validCities);

    var inputList = new ArrayList<String>(validCities);

    // when
    var actualCities = cityValidator.filter(inputList);

    // then
    assertThat(actualCities).hasSameElementsAs(validCities);
  }

  @Test
  void filter_whenInputListIsFullyContainedInTheValidCities_onlyValidReturned() {
    // given
    var cityValidator = new CityValidator(validCities);

    var inputList = List.of("Cluj-Napoca", "Arad");

    // when
    var actualCities = cityValidator.filter(inputList);

    // then
    assertThat(actualCities).hasSameElementsAs(inputList);
  }

  @Test
  void filter_whenInputListHasSomeElementsContainedInTheValidCities_onlyValidReturned() {
    // given
    var cityValidator = new CityValidator(validCities);

    var inputList = List.of("Cluj-Napoca", "Brasov", "Arad");

    // when
    var actualCities = cityValidator.filter(inputList);

    // then
    assertThat(actualCities).hasSameElementsAs(List.of("Cluj-Napoca", "Arad"));
  }

  @Test
  void filter_whenInputListHasNoneOfTheElementsContainedInTheValidCities_onlyValidReturned() {
    // given
    var cityValidator = new CityValidator(validCities);

    var inputList = List.of("Gherla", "Brasov");

    // when
    var actualCities = cityValidator.filter(inputList);

    // then
    assertThat(actualCities).isEmpty();
  }
}
