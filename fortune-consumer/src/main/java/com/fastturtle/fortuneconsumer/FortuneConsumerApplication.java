package com.fastturtle.fortuneconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FortuneConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortuneConsumerApplication.class, args);
    }

}
