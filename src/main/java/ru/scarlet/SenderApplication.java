package ru.scarlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.scarlet.feign.WeatherFeign;

@SpringBootApplication
@EnableFeignClients(clients = {WeatherFeign.class, ru.scarlet.feign.ExchangeFeign.class})
@EnableScheduling

public class SenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

}
