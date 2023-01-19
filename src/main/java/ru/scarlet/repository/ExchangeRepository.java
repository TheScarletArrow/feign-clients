package ru.scarlet.repository;

import org.springframework.data.repository.CrudRepository;
import ru.scarlet.entity.ExchangeRates;

public interface ExchangeRepository extends CrudRepository<ExchangeRates, Long> {
}

