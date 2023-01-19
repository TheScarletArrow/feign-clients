package ru.scarlet.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "WeatherFeign", url = "https://api.openweathermap.org/data/2.5/weather")
public interface WeatherFeign {

    @GetMapping("/")
    ResponseEntity<?> getWeather(@RequestParam String q, @RequestParam String appid, @RequestParam String units);
}
