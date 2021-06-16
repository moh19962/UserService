package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTestUnit {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;


    @Test
    void constructorTest() throws Exception {
        User user = new User(1L, "Moo", "Parwani", "mohammadparwaniu@outlook.com", "hidden");
        assertThat(user.getUserId()).isEqualTo(1L);
        assertThat(user.getFirstName()).isEqualTo("Moo");
    }

    @Test
    void nameNotEqual() throws Exception {
        //Arrange
        User user = new User(1L, "Moo", "Parwani", "mohammadparwaniu@outlook.com", "hidden");
        assertThat(user.getUserId()).isNotEqualTo(2L);
        assertThat(user.getFirstName()).isEqualTo("Moo");
    }
    
}
