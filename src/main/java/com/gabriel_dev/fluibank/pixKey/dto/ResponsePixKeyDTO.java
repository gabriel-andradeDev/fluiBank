package com.gabriel_dev.fluibank.pixKey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePixKeyDTO {

    private Long id;

    private String type;

    private String key;
}
