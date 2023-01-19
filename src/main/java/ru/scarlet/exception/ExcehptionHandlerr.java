package ru.scarlet.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcehptionHandlerr {

    @ExceptionHandler(value = WeatherNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWeatherNotFoundException(WeatherNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage(), 404, System.currentTimeMillis(), "/weather/"));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(WeatherNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage(), 404, System.currentTimeMillis(), "/weather/"));
    }
}
