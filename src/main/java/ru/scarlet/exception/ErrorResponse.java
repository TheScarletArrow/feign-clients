package ru.scarlet.exception;

public record ErrorResponse(String message, Integer status, Long timestamp, String path) {



}
