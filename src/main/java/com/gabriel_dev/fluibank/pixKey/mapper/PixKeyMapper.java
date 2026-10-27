package com.gabriel_dev.fluibank.pixKey.mapper;

import com.gabriel_dev.fluibank.pixKey.dto.ResponsePixKeyDTO;
import com.gabriel_dev.fluibank.pixKey.entity.PixKeyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PixKeyMapper {

    ResponsePixKeyDTO pixKeyEntityToResponsePixKeyDTO(PixKeyEntity pixKeyEntity);
}
