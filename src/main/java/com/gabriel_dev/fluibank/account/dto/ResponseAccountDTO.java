package com.gabriel_dev.fluibank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseAccountDTO {

    private Long id;

    private BigDecimal balance;

    private String numberAccount;

    private String userName;
}
