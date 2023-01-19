package ru.scarlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.service.WeatherService;

import java.io.IOException;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

private final WeatherService weatherService;
    @GetMapping("/")
    public ResponseEntity<?> getWeather(@RequestParam String q) throws IOException {
        return ResponseEntity.ok().body(weatherService.getWeather(q));
    }

}
