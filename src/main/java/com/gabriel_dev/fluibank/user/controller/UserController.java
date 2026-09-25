package com.gabriel_dev.fluibank.user.controller;


import com.gabriel_dev.fluibank.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    private UserRepository userRepository;


    @GetMapping
    public String getUsers() {
        return "Hello World!!";
    }
}
