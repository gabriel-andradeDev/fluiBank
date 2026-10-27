package com.gabriel_dev.fluibank.pixKey.service;

import com.gabriel_dev.fluibank.pixKey.dto.CreatePixKeyDTO;
import com.gabriel_dev.fluibank.pixKey.dto.ResponsePixKeyDTO;
import com.gabriel_dev.fluibank.pixKey.entity.PixKeyEntity;
import com.gabriel_dev.fluibank.pixKey.mapper.PixKeyMapper;
import com.gabriel_dev.fluibank.pixKey.repository.PixKeyRepository;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PixKeyService {
    private final PixKeyRepository pixKeyRepository;
    private final PixKeyMapper pixKeyMapper;

    public List<ResponsePixKeyDTO> getPixKeys(UserEntity user) {
        var pixKeys = pixKeyRepository.findAllByAccount_Id(user.getAccount().getId());
        return pixKeys.stream().map(pixKeyMapper::pixKeyEntityToResponsePixKeyDTO).toList();
    }

    public String createPixKey(CreatePixKeyDTO createPixKeyDTO, UserEntity user) throws BadRequestException {
        PixKeyEntity pixKeyExisting = pixKeyRepository.findByKey(createPixKeyDTO.getKey());
        if (pixKeyExisting != null) {
           throw new BadRequestException("Pix Key already exists");
        }

        PixKeyEntity pixKeyEntity = PixKeyEntity.builder()
                        .key(createPixKeyDTO.getKey())
                        .type(createPixKeyDTO.getType())
                        .account(user.getAccount())
                        .build();
        pixKeyRepository.save(pixKeyEntity);
        return "Pix Key created successfully!";
    }

    public void deletePixKey(Long id) {
        pixKeyRepository.deleteById(id);
    }
}
