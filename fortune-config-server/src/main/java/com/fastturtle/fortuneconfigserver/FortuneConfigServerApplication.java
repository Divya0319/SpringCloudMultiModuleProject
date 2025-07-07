package com.fastturtle.fortuneconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FortuneConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortuneConfigServerApplication.class, args);
    }

}
