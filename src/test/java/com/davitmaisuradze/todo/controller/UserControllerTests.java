package com.davitmaisuradze.todo.controller;

import com.davitmaisuradze.todo.dto.user.CreateUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql(scripts = "/test-schema.sql")
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateUser_WhenCreateUserDtoValid_ThenReturnIsCreated() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUsername("username");
        createUserDto.setFirstName("first");
        createUserDto.setLastName("last");

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateUser_WhenCreateUserDtoInvalid_ThenReturnBadRequest() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(status().isBadRequest());
    }
}
