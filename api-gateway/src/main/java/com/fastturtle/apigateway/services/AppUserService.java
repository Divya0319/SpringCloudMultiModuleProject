package com.fastturtle.apigateway.services;

import com.fastturtle.apigateway.models.AppUser;
import com.fastturtle.apigateway.models.Role;
import com.fastturtle.apigateway.repos.AppUserRepository;
import com.fastturtle.apigateway.repos.RolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser saveUser(String email, String rawPassword, String role) {
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));

        Role savedRole = rolesRepository.save(new Role(role));
        user.addRole(savedRole);
        return userRepository.save(user);
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}