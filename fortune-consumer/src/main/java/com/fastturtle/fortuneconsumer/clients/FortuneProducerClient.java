package com.fastturtle.fortuneconsumer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fortune-producer", url = "${fortune.producer.url}")
public interface FortuneProducerClient {

    @GetMapping("/fortunes/fetch")
    String getRandomFortune();
}
