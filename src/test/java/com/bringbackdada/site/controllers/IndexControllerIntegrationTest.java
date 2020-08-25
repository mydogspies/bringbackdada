package com.bringbackdada.site.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = IndexController.class)
class IndexControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getIndexPageResponse() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}