package com.gabriel_dev.fluibank.user.mapper;

import com.gabriel_dev.fluibank.user.dto.ResponseUserDTO;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    @Mapping(target = "active", source = "active")
    ResponseUserDTO toResponseUserDTO(UserEntity user);
}
