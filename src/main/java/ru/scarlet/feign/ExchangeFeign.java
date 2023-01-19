package ru.scarlet.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.scarlet.entity.fromjson.exchange.ExchangeFromJson;

@FeignClient(name = "ExchangeFeign", url = "https://openexchangerates.org/api/")
public interface ExchangeFeign {

    @GetMapping("/latest.json")
    ExchangeFromJson getExchange(@RequestParam String app_id);
}
