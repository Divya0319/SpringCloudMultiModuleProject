package com.fastturtle.fortuneproducer;

import com.fastturtle.fortuneproducer.services.FortuneProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FortuneProducerService.class)
public class FortuneProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortuneProducerApplication.class, args);
	}

}
