package ru.scarlet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "weather")
public class WeatherToTg {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String city;
    private Double temperature;
    private Double feelsLike;
    private Integer instant;

}
