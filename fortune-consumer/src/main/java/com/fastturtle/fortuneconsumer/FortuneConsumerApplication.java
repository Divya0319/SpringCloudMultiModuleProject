package com.fastturtle.fortuneconsumer;

import com.fastturtle.fortuneconsumer.services.FortuneConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FortuneConsumerService.class)
public class FortuneConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortuneConsumerApplication.class, args);
    }

}
