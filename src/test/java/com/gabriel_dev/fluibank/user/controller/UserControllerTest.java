package com.gabriel_dev.fluibank.user.controller;

import com.gabriel_dev.fluibank.configs.JwtService;
import com.gabriel_dev.fluibank.user.entity.UserEntity;
import com.gabriel_dev.fluibank.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private JwtService jwtService;

    @Test
    void shouldCreateAccountWhenPayloadIsValid() throws Exception {
        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        when(userRepository.existsByCpf("12345678900")).thenReturn(false);
        when(userRepository.existsByPhone("11999990000")).thenReturn(false);
        when(passwordEncoder.encode("secret123")).thenReturn("encoded-secret123");

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "John Doe",
                                  "email": "john@example.com",
                                  "password": "secret123",
                                  "cpf": "12345678900",
                                  "phone": "11999990000"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Account created successfully"));

        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userCaptor.capture());
        UserEntity savedUser = userCaptor.getValue();

        assertThat(savedUser.getPassword()).isEqualTo("encoded-secret123");
        assertThat(savedUser.isActive()).isTrue();
        assertThat(savedUser.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldReturnConflictWhenEmailAlreadyExists() throws Exception {
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "John Doe",
                                  "email": "john@example.com",
                                  "password": "secret123",
                                  "cpf": "12345678900",
                                  "phone": "11999990000"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email already in use"));

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void shouldReturnConflictWhenCpfAlreadyExists() throws Exception {
        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        when(userRepository.existsByCpf("12345678900")).thenReturn(true);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "John Doe",
                                  "email": "john@example.com",
                                  "password": "secret123",
                                  "cpf": "12345678900",
                                  "phone": "11999990000"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("CPF already in use"));

        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
