package com.gabriel_dev.fluibank.user.mapper;

import com.gabriel_dev.fluibank.user.dto.ResponseUserDTO;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    ResponseUserDTO toResponseUserDTO(UserEntity user);
}
