package com.gabriel_dev.fluibank.account.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {


    @GetMapping
    public String getAccounts() {
        return "Accounts endpoint is working!";
    }
}
