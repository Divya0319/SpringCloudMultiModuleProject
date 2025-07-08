package com.fastturtle.fortuneproducer.controllers;

import com.fastturtle.fortuneproducer.services.FortuneProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/fortunes")
public class FortuneController {

    private final FortuneProducerService fortuneProducerService;

    public FortuneController(FortuneProducerService fortuneProducerService) {
        this.fortuneProducerService = fortuneProducerService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> getFortune() {
        Random r = new Random();
        List<String> fortunes = fortuneProducerService.getList();
        int index = r.nextInt(fortunes.size());

        return ResponseEntity.ok(fortunes.get(index));
    }

}
