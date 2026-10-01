package com.gabriel_dev.fluibank.user.dto;

import com.gabriel_dev.fluibank.account.entity.AccountEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private AccountEntity account;
}
