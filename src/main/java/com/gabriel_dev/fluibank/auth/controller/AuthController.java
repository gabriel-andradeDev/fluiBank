package com.gabriel_dev.fluibank.auth.controller;

import com.gabriel_dev.fluibank.auth.dto.LoginDTO;
import com.gabriel_dev.fluibank.auth.dto.ResponseLogin;
import com.gabriel_dev.fluibank.auth.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping
    public ResponseLogin login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
