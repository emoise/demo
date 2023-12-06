package com.yonder.demo.controller;

import com.yonder.demo.dto.out.WeatherResultOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Tag(name = "Weather")
public interface WeatherControllerApi {

  @Operation(
      operationId = "get-temperature",
      summary = "Get the weather average for the specified cities")
  @ApiResponse(responseCode = "200", description = "Temperature average successfully returned")
  @ApiResponse(
      responseCode = "400",
      description = "Validation exception",
      content =
          @Content(
              schema = @Schema(implementation = Map.class),
              mediaType = "application/problem+json"))
  ResponseEntity<WeatherResultOutput> getWeather(List<String> cities);
}
