package com.gabriel_dev.fluibank.user.controller;


import com.gabriel_dev.fluibank.user.dto.CreateUserDTO;
import com.gabriel_dev.fluibank.user.dto.ResponseUserDTO;
import com.gabriel_dev.fluibank.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public String getUsers() {
        return "Hello World!";
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> registerUser(@RequestBody @Valid CreateUserDTO userDTO) {
        return this.userService.registerUser(userDTO);
    }

}
