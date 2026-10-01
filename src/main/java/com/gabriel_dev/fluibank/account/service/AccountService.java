package com.gabriel_dev.fluibank.account.service;

import com.gabriel_dev.fluibank.account.dto.ResponseAccountDTO;
import com.gabriel_dev.fluibank.account.entity.AccountEntity;
import com.gabriel_dev.fluibank.account.repository.AccountRepository;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public void createAccount(UserEntity userEntity) {
        String number;

        do {
            number = String.format("%08d", ThreadLocalRandom.current().nextInt(0, 100_000_000));
        } while (accountRepository.existsByNumberAccount(number));

        AccountEntity accountEntity = AccountEntity.builder()
                .numberAccount(number)
                .balance(BigDecimal.valueOf(0.0))
                .user(userEntity)
                .build();

        userEntity.setAccount(accountEntity);
        accountRepository.save(accountEntity);
    }


}
