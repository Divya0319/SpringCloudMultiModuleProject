package com.fastturtle.fortuneconsumer.services;

import com.fastturtle.fortuneconsumer.clients.FortuneProducerClient;
import com.fastturtle.fortuneconsumer.models.Fortune;
import com.fastturtle.fortuneconsumer.repos.FortuneRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FortuneService {

    private final FortuneRepo fortuneRepo;

    private final FortuneProducerClient fortuneProducerClient;

    public FortuneService(FortuneRepo fortuneRepo, FortuneProducerClient fortuneProducerClient) {
        this.fortuneRepo = fortuneRepo;
        this.fortuneProducerClient = fortuneProducerClient;
    }

    public Fortune saveGeneratedFortune(Fortune fortune) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        fortune.setTimestamp(now);

        return fortuneRepo.save(fortune);
    }

    public Fortune predictAndSaveFortune() {
        String fortuneGenerated = fortuneProducerClient.getRandomFortune();

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        Fortune fortune = new Fortune();
        fortune.setGeneratedFortune(fortuneGenerated);
        fortune.setTimestamp(now);

        return fortuneRepo.save(fortune);
    }
}
