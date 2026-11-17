package com.gabriel_dev.fluibank.deposit.controller;

import com.gabriel_dev.fluibank.deposit.dto.DepositDTO;
import com.gabriel_dev.fluibank.deposit.entity.DepositEntity;
import com.gabriel_dev.fluibank.deposit.service.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deposit")
@AllArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @PostMapping
    public String deposit(@RequestBody DepositDTO depositDTO) {
        return depositService.deposit(depositDTO);
    }

    @GetMapping
    public List<DepositEntity> getDeposits() {
        return depositService.getDeposits();
    }
}
