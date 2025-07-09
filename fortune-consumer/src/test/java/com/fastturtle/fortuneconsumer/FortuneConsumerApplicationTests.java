package com.fastturtle.fortuneconsumer;

import com.fastturtle.fortuneconsumer.models.Fortune;
import com.fastturtle.fortuneconsumer.repos.FortuneRepo;
import com.fastturtle.fortuneconsumer.services.FortuneConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active=test")
class FortuneConsumerApplicationTests {

    @Autowired
    private FortuneConsumerService fortuneConsumerService;

    @Autowired
    private FortuneRepo fortuneRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSaveFortune() {
        Fortune fortune = new Fortune();

        fortune.setGeneratedFortune("This is your lucky day!");

        Fortune saved = fortuneConsumerService.saveGeneratedFortune(fortune);

        // Assert
        assertThat(saved.getId()).isNotNull();
        assertThat(fortuneRepo.findById(saved.getId())).isPresent();
    }

}
