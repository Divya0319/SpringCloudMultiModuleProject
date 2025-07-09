package com.fastturtle.fortuneconsumer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fortune-producer", url = "${fortune.producer.url}", fallback = FortuneFallback.class)
public interface FortuneProducerClient {

    @GetMapping("/fortunes/fetch")
    String getRandomFortune();
}
