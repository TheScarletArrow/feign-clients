package ru.scarlet.entity.fromjson;

import lombok.Data;

import java.util.List;

@Data
public class WeatherFromJson {
    private Coord coord;
    private List<Weather> weather;

    private String base;

    private Main main;

    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;
}
