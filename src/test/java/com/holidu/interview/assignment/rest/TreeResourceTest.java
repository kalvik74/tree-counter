package com.holidu.interview.assignment.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TreeResourceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void trees() throws Exception {
        MvcResult resultActions =  mockMvc.perform(
                get("/trees")
                        .param("x", "1027632.259")
                        .param("y", "254897.07")
                        .param("radius", "10")
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
        Map trees = objectMapper.readValue(resultActions.getResponse().getContentAsString(), Map.class);
        assertTrue(trees.containsKey("pin oak"));
    }
}