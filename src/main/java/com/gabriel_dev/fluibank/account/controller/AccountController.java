package com.gabriel_dev.fluibank.account.controller;

import com.gabriel_dev.fluibank.account.dto.ResponseAccountDTO;
import com.gabriel_dev.fluibank.account.repository.AccountRepository;
import com.gabriel_dev.fluibank.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<ResponseAccountDTO> getAccounts() {
        return this.accountService.getAllAccounts();
    }
}
