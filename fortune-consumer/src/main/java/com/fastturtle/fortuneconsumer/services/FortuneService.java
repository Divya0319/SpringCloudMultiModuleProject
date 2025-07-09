package com.fastturtle.fortuneconsumer.services;

import com.fastturtle.fortuneconsumer.models.Fortune;
import com.fastturtle.fortuneconsumer.repos.FortuneRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FortuneService {

    private final FortuneRepo fortuneRepo;

    public FortuneService(FortuneRepo fortuneRepo) {
        this.fortuneRepo = fortuneRepo;
    }

    public Fortune saveGeneratedFortune(Fortune fortune) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        fortune.setTimestamp(now);

        return fortuneRepo.save(fortune);
    }
}
