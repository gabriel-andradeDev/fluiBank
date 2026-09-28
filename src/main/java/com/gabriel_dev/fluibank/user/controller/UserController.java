package com.gabriel_dev.fluibank.user.controller;


import com.gabriel_dev.fluibank.user.dto.CreateAccountRequest;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import com.gabriel_dev.fluibank.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public String getUsers() {
        return "Hello World!";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createAccount(@RequestBody CreateAccountRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already in use"));
        }

        if (userRepository.existsByCpf(request.cpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "CPF already in use"));
        }

        if (request.phone() != null && userRepository.existsByPhone(request.phone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Phone already in use"));
        }

        UserEntity userEntity = UserEntity.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .cpf(request.cpf())
                .phone(request.phone())
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Account created successfully"));
    }
}
