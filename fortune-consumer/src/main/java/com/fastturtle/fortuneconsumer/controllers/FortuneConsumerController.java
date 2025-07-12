package com.fastturtle.fortuneconsumer.controllers;

import com.fastturtle.fortuneconsumer.dtos.FortuneDTO;
import com.fastturtle.fortuneconsumer.models.Fortune;
import com.fastturtle.fortuneconsumer.services.FortuneConsumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/fortunes")
public class FortuneConsumerController {

    private final FortuneConsumerService fortuneService;

    public FortuneConsumerController(FortuneConsumerService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @GetMapping("/predict")
    public FortuneDTO predict() {
        Fortune predictedFortune = fortuneService.predictAndSaveFortune();
        return from(predictedFortune);
    }

    @GetMapping("/some-api")
    public ResponseEntity<String> check(@RequestHeader Map<String, String> headers) {
        headers.forEach((k, v) -> System.out.println(k + " => " + v));
        return ResponseEntity.ok("ok");
    }

    public FortuneDTO from(Fortune fortune) {
        FortuneDTO fortuneDTO = new FortuneDTO();
        fortuneDTO.setYourFortune(fortune.getGeneratedFortune());

        // convert to "27/03/2025" 07:26 PM datetime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        String formattedTimestamp = fortune.getTimestamp().format(formatter);

        fortuneDTO.setPredictedAt(formattedTimestamp);

        return fortuneDTO;
    }
}
