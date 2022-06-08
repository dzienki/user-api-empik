package com.dzienki.userapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dzienki.userapi.controller.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUser_shouldReturnSameLogin() throws Exception {
        //given
        var username = "dzienki";
        //when
        var result = mockMvc.perform(get("/user/" + username));
        //then
        result.andExpect(status().isOk());
        var user = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
            UserDto.class);
        assertEquals(username, user.getLogin());
    }
}