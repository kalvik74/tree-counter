package com.holidu.interview.assignment.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class TreeCounterResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void trees() throws Exception {
        mockMvc.perform(
                get("/tree")
                        .param("x", "1027632.259")
                        .param("y", "254897.07")
                        .param("radius", "10")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("pin oak")))
        ;
    }
}