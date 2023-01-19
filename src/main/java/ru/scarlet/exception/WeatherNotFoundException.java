package ru.scarlet.exception;

public class WeatherNotFoundException extends RuntimeException {
    public WeatherNotFoundException(String message) {
        super(message);
    }

    public WeatherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherNotFoundException(Throwable cause) {
        super(cause);
    }

    public WeatherNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WeatherNotFoundException() {
    }
}
