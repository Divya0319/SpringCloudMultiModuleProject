package com.fastturtle.fortuneconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class FortuneConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortuneConsumerApplication.class, args);
    }

}
