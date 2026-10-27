package com.gabriel_dev.fluibank.deposit.service;

import com.gabriel_dev.fluibank.account.entity.AccountEntity;
import com.gabriel_dev.fluibank.account.repository.AccountRepository;
import com.gabriel_dev.fluibank.deposit.dto.DepositDTO;
import com.gabriel_dev.fluibank.deposit.entity.DepositEntity;
import com.gabriel_dev.fluibank.deposit.repository.DepositRepository;
import com.gabriel_dev.fluibank.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepositService {

    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;

    @Transactional
    public String deposit(DepositDTO depositDTO) {
        AccountEntity accountExists = accountRepository.findByNumberAccount(depositDTO.getNumberAccount());

        System.out.println("Account found: " + accountExists);

        if (accountExists == null) {
            return "Account not found";
        }

        var currentBalance = accountExists.getBalance();
        var newBalance = currentBalance.add(depositDTO.getValue());
        accountExists.setBalance(newBalance);
        accountRepository.save(accountExists);
        depositRepository.save(DepositEntity.builder()
                .numberAccount(depositDTO.getNumberAccount())
                .value(depositDTO.getValue())
                .build());

        return "Deposit successful";
    }

    public List<DepositEntity> getDeposits() {
        return depositRepository.findAll();
    }
}
