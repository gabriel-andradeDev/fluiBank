package com.gabriel_dev.fluibank.deposit.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DepositDTO {

    private String numberAccount;
    private BigDecimal value;
}
