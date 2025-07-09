package com.fastturtle.fortuneconsumer.dtos;

public class FortuneDTO {

    private String yourFortune;

    private String predictedAt;

    public String getYourFortune() {
        return yourFortune;
    }

    public void setYourFortune(String yourFortune) {
        this.yourFortune = yourFortune;
    }

    public String getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(String predictedAt) {
        this.predictedAt = predictedAt;
    }
}
