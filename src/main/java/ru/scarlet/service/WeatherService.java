package ru.scarlet.service;

import ru.scarlet.entity.WeatherToTg;

import java.io.IOException;

public interface WeatherService {
    WeatherToTg getWeather(String city) throws IOException;
}
