package com.fastturtle.fortuneconsumer.controllers;

import com.fastturtle.fortuneconsumer.services.FortuneConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/fortunes")
public class FortuneController {

    private final FortuneConsumerService fortuneConsumerService;

    public FortuneController(FortuneConsumerService fortuneConsumerService) {
        this.fortuneConsumerService = fortuneConsumerService;
    }

    @GetMapping("/fetch")
    public String getFortune() {
        Random r = new Random();
        List<String> fortunes = fortuneConsumerService.getList();
        int index = r.nextInt(fortunes.size());

        return fortunes.get(index);
    }

}
