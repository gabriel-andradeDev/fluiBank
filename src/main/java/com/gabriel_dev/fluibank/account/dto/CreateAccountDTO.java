package com.gabriel_dev.fluibank.account.dto;

import com.gabriel_dev.fluibank.user.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountDTO {


    private String numberAccount;

    private BigDecimal balance;

    private UserEntity user;
}
