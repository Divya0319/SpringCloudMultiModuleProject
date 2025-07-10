package com.fastturtle.fortuneconsumer.services;

import com.fastturtle.fortuneconsumer.clients.FortuneProducerClient;
import com.fastturtle.fortuneconsumer.models.Fortune;
import com.fastturtle.fortuneconsumer.repos.FortuneRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FortuneConsumerService {

    private final FortuneRepo fortuneRepo;

    private final FortuneProducerClient fortuneProducerClient;

    public FortuneConsumerService(FortuneRepo fortuneRepo, FortuneProducerClient fortuneProducerClient) {
        this.fortuneRepo = fortuneRepo;
        this.fortuneProducerClient = fortuneProducerClient;
    }

    public Fortune saveGeneratedFortune(Fortune fortune) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        fortune.setTimestamp(now);

        return fortuneRepo.save(fortune);
    }


    @Retry(name = "fortuneRetry" , fallbackMethod = "getRandomFortuneRetryFallback")
//    @CircuitBreaker(name = "fortuneCircuitBreaker", fallbackMethod = "getRandomFortuneFallback")
    public Fortune predictAndSaveFortune() {
        String fortuneGenerated = fortuneProducerClient.getRandomFortune();

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        Fortune fortune = new Fortune();
        fortune.setGeneratedFortune(fortuneGenerated);
        fortune.setTimestamp(now);

        return fortuneRepo.save(fortune);
    }

    public Fortune getRandomFortuneFallback(Exception ex) {
        Fortune fortune = new Fortune();
        fortune.setGeneratedFortune("Circuit is open! Fallback: Fortune service is unavailable. Error: " + ex.getMessage());
        fortune.setTimestamp(LocalDateTime.now());
        return fortune;
    }

    public Fortune getRandomFortuneRetryFallback(Exception ex) {
        Fortune fortune = new Fortune();
        fortune.setGeneratedFortune("Retry exhausted. Error: " + ex.getMessage());
        fortune.setTimestamp(LocalDateTime.now());
        return fortune;
    }
}
