package com.bringbackdada.site.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = AboutController.class)
class AboutControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAboutPageResponse() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk());
    }
}