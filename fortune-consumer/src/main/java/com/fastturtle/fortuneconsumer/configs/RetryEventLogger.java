package com.fastturtle.fortuneconsumer.configs;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryEventLogger {

    private final Retry retry;

    public RetryEventLogger(RetryRegistry retryRegistry) {
        this.retry = retryRegistry.retry("fortune-producer");
    }

    @PostConstruct
    public void registerRetryListener() {
        retry.getEventPublisher().onRetry(event -> {
            System.out.println("Retry attempt: " + event.getNumberOfRetryAttempts()
                    + " due to: " + event.getLastThrowable().getMessage());
        });
    }
}