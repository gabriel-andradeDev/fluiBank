package com.gabriel_dev.fluibank.pixKey.dto;

import com.gabriel_dev.fluibank.pixKey.enums.PixKeyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreatePixKeyDTO {

    private PixKeyType type;

    private String key;
}
