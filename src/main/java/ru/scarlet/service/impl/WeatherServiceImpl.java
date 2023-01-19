package ru.scarlet.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.scarlet.entity.WeatherToTg;
import ru.scarlet.entity.fromjson.weather.WeatherFromJson;
import ru.scarlet.exception.WeatherNotFoundException;
import ru.scarlet.feign.WeatherFeign;
import ru.scarlet.repository.WeatherRepository;
import ru.scarlet.service.WeatherService;

@RequiredArgsConstructor
@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {
    private final WeatherFeign weatherFeign;

    @Value("${weather.api.key}")
    private String apiKey;
    @Value("${weather.api.units}")
    private String units;

    private final WeatherRepository weatherRepository;

    @Override
    public WeatherToTg getWeather(String city) {
        try {
            ResponseEntity<?> weather = weatherFeign.getWeather(city, apiKey, units);

            if (weather.getStatusCode().value() == 200) {
                Object body = weather.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                WeatherFromJson weatherFromJson = objectMapper.readValue(objectMapper.writeValueAsString(body), WeatherFromJson.class);
                log.info("Weather: {}", weatherFromJson);
                WeatherToTg weatherToTg = new WeatherToTg();
                weatherToTg.setCity(weatherFromJson.getName());
                weatherToTg.setTemperature(weatherFromJson.getMain().getTemp());
                weatherToTg.setInstant(weatherFromJson.getDt());
                weatherToTg.setFeelsLike(weatherFromJson.getMain().getFeelsLike());
                weatherRepository.save(weatherToTg);
                return weatherToTg;
            } else throw new IllegalArgumentException("Error");
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new WeatherNotFoundException("Error");
        }
    }

    @Scheduled(fixedDelay = 600_000L)
    public void getWeather() {
        getWeather("Moscow");
    }

}
