package com.gabriel_dev.fluibank.user.service;

import com.gabriel_dev.fluibank.account.entity.AccountEntity;
import com.gabriel_dev.fluibank.account.repository.AccountRepository;
import com.gabriel_dev.fluibank.account.service.AccountService;
import com.gabriel_dev.fluibank.user.dto.CreateUserDTO;
import com.gabriel_dev.fluibank.user.dto.ResponseUserDTO;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import com.gabriel_dev.fluibank.user.mapper.ResponseMapper;
import com.gabriel_dev.fluibank.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AccountService accountService;
    private final ResponseMapper responseMapper;

    @Transactional
    public ResponseEntity<ResponseUserDTO> registerUser(CreateUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().build();
        }


        UserEntity userEntity = UserEntity.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .cpf(userDTO.getCpf())
                .phone(userDTO.getPhone())
                .active(true)
                .build();

        userRepository.save(userEntity);
        accountService.createAccount(userEntity);
        ResponseUserDTO responseUserDTO = responseMapper.toResponseUserDTO(userEntity);
        return ResponseEntity.ok(responseUserDTO);
    }
}
