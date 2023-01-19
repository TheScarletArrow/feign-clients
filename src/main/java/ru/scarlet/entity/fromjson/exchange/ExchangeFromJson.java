package ru.scarlet.entity.fromjson.exchange;

import lombok.Data;

@Data
public class ExchangeFromJson {
    String disclaimer;
    String license;
    Long timestamp;

    String base;

    Rates rates;
}
