package com.fastturtle.fortuneconsumer.services;

import com.fastturtle.fortuneconsumer.models.Fortune;
import com.fastturtle.fortuneconsumer.repos.FortuneRepo;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FortuneConsumerService {

    private final RestTemplate restTemplate;

    private final FortuneRepo fortuneRepo;

    @Value("${fortune.producer.url}")
    private String fortuneProducerUrl;


    private static final Logger logger = LoggerFactory.getLogger(FortuneConsumerService.class);

    public FortuneConsumerService(RestTemplate restTemplate, FortuneRepo fortuneRepo) {
        this.restTemplate = restTemplate;
        this.fortuneRepo = fortuneRepo;
    }

    public Fortune saveGeneratedFortune(Fortune fortune) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        fortune.setTimestamp(now);

        return fortuneRepo.save(fortune);
    }

    @Retry(name = "fortuneRetry", fallbackMethod = "getRandomFortuneRetryFallback")
    public Fortune predictAndSaveFortune() {
        String fortuneGenerated = fetchFortuneWithRetry();

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        Fortune fortune = new Fortune();
        fortune.setGeneratedFortune(fortuneGenerated);
        fortune.setTimestamp(now);

        if(!fortuneGenerated.contains("Retry")) {
            return fortuneRepo.save(fortune);
        }

        return fortune;
    }

    public String fetchFortuneWithRetry() {
        return restTemplate.getForObject(fortuneProducerUrl + "/fortunes/fetch", String.class);
    }

    public Fortune getRandomFortuneRetryFallback(Exception ex) {
        logger.info("Retry exhausted. {}", ex.getMessage());
        Fortune fortune = new Fortune();
        fortune.setGeneratedFortune("Retry exhausted. {}" + ex.getMessage());
        fortune.setTimestamp(LocalDateTime.now());
        return fortune;
    }
}
