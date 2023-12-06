package com.yonder.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WeatherControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  void getWeather_whenOneValidCityInput_oneCityWithAverageReturned() throws Exception {
    // given

    // when
    var resultActions = mockMvc.perform(get("/api/weather?cities=Cluj-Napoca"));
    var response = resultActions.andReturn().getResponse().getContentAsString();

    // then
    resultActions.andExpect(status().is2xxSuccessful());
    assertThat(response)
        .isEqualTo("""
        {"result":[{"name":"Cluj-Napoca","temperature":2.0,"wind":3.0}]}""");
  }

  @Test
  void getWeather_whenNoValidCityInput_emptyResponseReturned() throws Exception {
    // given

    // when
    var resultActions = mockMvc.perform(get("/api/weather?cities=Mizil"));
    var response = resultActions.andReturn().getResponse().getContentAsString();

    // then
    resultActions.andExpect(status().is2xxSuccessful());
    assertThat(response).isEqualTo("""
        {"result":[]}""");
  }

  @Test
  void getWeather_whenBothValidAndInvalidCitiesInput_oneCitiesWithAverageReturned()
      throws Exception {
    // given

    // when
    var resultActions =
        mockMvc.perform(get("/api/weather?cities=Cluj-Napoca,Brasov,Arad,Bucuresti"));
    var response = resultActions.andReturn().getResponse().getContentAsString();

    // then
    resultActions.andExpect(status().is2xxSuccessful());
    assertThat(response)
        .isEqualTo(
            """
        {"result":[{"name":"Arad","temperature":-0.25,"wind":4.5},{"name":"Bucuresti","temperature":4.5,"wind":2.25},{"name":"Cluj-Napoca","temperature":2.0,"wind":3.0}]}""");
  }
}
