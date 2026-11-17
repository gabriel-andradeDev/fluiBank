package com.gabriel_dev.fluibank.user.controller;

import com.gabriel_dev.fluibank.configs.JwtService;
import com.gabriel_dev.fluibank.user.dto.CreateUserDTO;
import com.gabriel_dev.fluibank.user.repository.UserRepository;
import com.gabriel_dev.fluibank.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void shouldRegisterUserWhenPayloadIsValid() throws Exception {
        when(userService.registerUser(any(CreateUserDTO.class)))
                .thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(post("/api/user/register")
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
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenServiceRejectsRegistration() throws Exception {
        when(userService.registerUser(any(CreateUserDTO.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        mockMvc.perform(post("/api/user/register")
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
                .andExpect(status().isBadRequest());
    }
}
