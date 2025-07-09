package com.fastturtle.fortuneconsumer.clients;


public class FortuneFallback implements FortuneProducerClient {

    @Override
    public String getRandomFortune() {
        return "Fallback: Fortune service is unavailable right now.";
    }
}
