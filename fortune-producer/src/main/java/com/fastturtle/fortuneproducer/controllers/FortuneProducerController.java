package com.fastturtle.fortuneproducer.controllers;

import com.fastturtle.fortuneproducer.services.FortuneProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/fortunes")
public class FortuneProducerController {

    private final FortuneProducerService fortuneProducerService;

    public FortuneProducerController(FortuneProducerService fortuneProducerService) {
        this.fortuneProducerService = fortuneProducerService;
    }

    @GetMapping("/fetch")
    public String getFortune() {
        Random r = new Random();
        List<String> fortunes = fortuneProducerService.getList();
        int index = r.nextInt(fortunes.size());

        return fortunes.get(index);
    }

    @GetMapping("/test")
    public Map<String, String> test() {

        return Map.of("Test", "test");
    }

    @GetMapping("/some-api")
    public ResponseEntity<String> check(@RequestHeader Map<String, String> headers) {
        headers.forEach((k, v) -> System.out.println(k + " => " + v));
        return ResponseEntity.ok("ok");
    }

}
