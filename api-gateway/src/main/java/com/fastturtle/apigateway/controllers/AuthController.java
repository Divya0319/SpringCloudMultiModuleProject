package com.fastturtle.apigateway.controllers;

import com.fastturtle.apigateway.models.AppUser;
import com.fastturtle.apigateway.services.AppUserService;
import com.fastturtle.apigateway.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserService appUserService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        Optional<AppUser> userOptional = appUserService.findByEmail(email);

        if(!StringUtils.isBlank(email) && !StringUtils.isBlank(password)) {
            if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
                String token = jwtUtil.generateToken(email);
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
