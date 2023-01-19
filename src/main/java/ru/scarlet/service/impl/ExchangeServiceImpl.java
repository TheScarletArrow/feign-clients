package ru.scarlet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.scarlet.entity.ExchangeRates;
import ru.scarlet.entity.fromjson.exchange.ExchangeFromJson;
import ru.scarlet.feign.ExchangeFeign;
import ru.scarlet.repository.ExchangeRepository;
import ru.scarlet.service.ExchangeService;

@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeFeign exchangeFeign;
    private final ExchangeRepository exchangeRepository;
    @Value("${openexchangerates.api.key}")
    private String apiKey;
    @Override
    public ExchangeRates getExchangeRates() {
        ExchangeFromJson exchange = exchangeFeign.getExchange(apiKey);
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setCost(exchange.getRates().getRUB());
        exchangeRates.setDate(exchange.getTimestamp());
        exchangeRates.setCurrency("RUB");
        exchangeRepository.save(exchangeRates);
        return exchangeRates;
    }

    @Scheduled(fixedDelay = 600_000L)
    public void schedule() {
        getExchangeRates();
    }
}
