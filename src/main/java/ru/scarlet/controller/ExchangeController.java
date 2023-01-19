package ru.scarlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.service.ExchangeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @GetMapping("/rates")
    public ResponseEntity<?> getExchangeRates() {
        return ResponseEntity.ok(exchangeService.getExchangeRates());
    }
}
