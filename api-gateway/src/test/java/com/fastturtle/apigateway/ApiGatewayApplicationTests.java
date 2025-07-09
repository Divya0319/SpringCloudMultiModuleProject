package com.fastturtle.apigateway;

import com.fastturtle.apigateway.models.AppUser;
import com.fastturtle.apigateway.repos.AppUserRepository;
import com.fastturtle.apigateway.services.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active=test")
class ApiGatewayApplicationTests {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSaveAppUser() {
        AppUser saved = appUserService.saveUser("johndoe@gmail.com", "doe@123");

        // Assert
        assertThat(saved.getId()).isNotNull();
        assertThat(appUserRepository.findById(saved.getId())).isPresent();
    }

}
