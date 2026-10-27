package com.gabriel_dev.fluibank.pixKey.controller;

import com.gabriel_dev.fluibank.pixKey.dto.CreatePixKeyDTO;
import com.gabriel_dev.fluibank.pixKey.dto.ResponsePixKeyDTO;
import com.gabriel_dev.fluibank.pixKey.entity.PixKeyEntity;
import com.gabriel_dev.fluibank.pixKey.service.PixKeyService;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pix-keys")
@AllArgsConstructor
public class PixKeyController {

    private PixKeyService pixKeyService;

    @GetMapping
    public List<ResponsePixKeyDTO> getPixKeys(@CurrentSecurityContext(expression = "authentication.principal")UserEntity user) {
        return pixKeyService.getPixKeys(user);
    }

    @PostMapping
    public String createPixKey(@RequestBody CreatePixKeyDTO createPixKeyDTO, @CurrentSecurityContext(expression = "authentication.principal")UserEntity user) throws BadRequestException {
        pixKeyService.createPixKey(createPixKeyDTO, user);
        return "Pix Key created";
    }
}
