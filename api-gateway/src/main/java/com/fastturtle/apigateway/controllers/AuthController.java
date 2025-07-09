package com.fastturtle.apigateway.controllers;

import com.fastturtle.apigateway.models.AppUser;
import com.fastturtle.apigateway.services.AppUserService;
import com.fastturtle.apigateway.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserService appUserService;

    private final JwtUtil jwtUtil;

    private final Set<String> allowedEmails = Set.of("divygupta0319@gmail.com", "divya@gmail.com");

    public AuthController(AppUserService appUserService, JwtUtil jwtUtil) {
        this.appUserService = appUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        Optional<AppUser> appUserOptional = appUserService.findByEmail(email);

        if(appUserOptional.isPresent()) {
            String token = jwtUtil.generateToken(email);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
    }
}
