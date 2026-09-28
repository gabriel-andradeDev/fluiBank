package com.gabriel_dev.fluibank.user.dto;

public record CreateAccountRequest(
        String name,
        String email,
        String password,
        String cpf,
        String phone
) {
}
