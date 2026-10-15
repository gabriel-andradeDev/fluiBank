package com.gabriel_dev.fluibank.auth.service;

import com.gabriel_dev.fluibank.auth.dto.LoginDTO;
import com.gabriel_dev.fluibank.auth.dto.ResponseLogin;
import com.gabriel_dev.fluibank.configs.JwtService;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import com.gabriel_dev.fluibank.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@AllArgsConstructor
@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public ResponseLogin login(LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );

        } catch (Exception ex) {
            throw new IllegalStateException("Falha na autenticação: " + ex.getMessage());
        }

        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalStateException("Usuário autenticado não encontrado"));

        String token = jwtService.generateToken(user);
        return new ResponseLogin(token);
    }
}
