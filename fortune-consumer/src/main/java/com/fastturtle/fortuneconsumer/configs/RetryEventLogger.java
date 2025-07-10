package com.fastturtle.fortuneconsumer.configs;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryEventLogger {

    private final Retry retry;

    private static final Logger logger = LoggerFactory.getLogger(RetryEventLogger.class);

    public RetryEventLogger(RetryRegistry retryRegistry) {
        this.retry = retryRegistry.retry("fortuneRetry");
    }

    @PostConstruct
    public void registerRetryListener() {
        retry.getEventPublisher().onRetry(event -> {
            logger.info("Retry attempt: {} due to: {}",  event.getNumberOfRetryAttempts(), event.getLastThrowable().getMessage());
        });
    }
}