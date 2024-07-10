package com.intuit.gaming.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoreBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAccountUpdate_returnUserNotFound() throws Exception {
        this.mockMvc
                .perform(put("/api/account/update/{id}", "0").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isOnline\":true}"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User details not found"));
    }
}
