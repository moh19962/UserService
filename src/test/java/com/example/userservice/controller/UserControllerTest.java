package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class UserControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void saveUser() throws Exception {
        User user = new User(1L,"moo","parwani","mohammadparwani@outlook.com","test1996");

        String UserAssString = mapper.writeValueAsString(user);

        mvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserAssString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.firstName").value("moo"))
                .andExpect(jsonPath("$.lastName").value("parwani"))
                .andExpect(jsonPath("$.email").value("mohammadparwani@outlook.com"))
                .andExpect(jsonPath("$.password").value("test1996"));
    }


    @Test
    void findUserById() throws Exception {
        mvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.firstName").value("moo"))
                .andExpect(jsonPath("$.lastName").value("parwani"))
                .andExpect(jsonPath("$.email").value("mohammadparwani@outlook.com"))
                .andExpect(jsonPath("$.password").value("test1996"));
    }
//
    @Test
    void deleteAdminById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/user/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
//
//    @Test
//    void getAllAdmins()  throws Exception{
//        mvc.perform(get("/user/all")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].userId").value(1L))
//                .andExpect(jsonPath("$[0].firstName").value("moo"))
//                .andExpect(jsonPath("$[0].lastName").value("parwani"))
//                .andExpect(jsonPath("$[0].email").value("mohammadparwani@outlook.com"))
//                .andExpect(jsonPath("$[0].password").value("test1996"))
//                .andExpect(jsonPath("$[0].userId").value(1L))
//                .andExpect(jsonPath("$[0].firstName").value("timur"))
//                .andExpect(jsonPath("$[0].lastName").value("parwani"))
//                .andExpect(jsonPath("$[0].email").value("timurparwani@outlook.com"))
//                .andExpect(jsonPath("$[0].password").value("timur1996"))
//                .andExpect(jsonPath("$[0].userId").value(1L))
//                .andExpect(jsonPath("$[0].firstName").value("Luis"))
//                .andExpect(jsonPath("$[0].lastName").value("victoria"))
//                .andExpect(jsonPath("$[0].email").value("luisvictoria@outlook.com"))
//                .andExpect(jsonPath("$[0].password").value("test1996"));
//    }

}
