package com.intuit.gaming.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.gaming.model.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerSignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testNewUserSignup_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Player player = Player.builder()
                .playerName("Test")
                .age(25)
                .contact("7011567594")
                .build();
        this.mockMvc.perform(post("/player/signUp").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(HttpStatus.CREATED.getReasonPhrase()));
    }

    @Test
    void testNewUserSignup_returnConflictException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Player player1 = Player.builder()
                .playerName("Test1")
                .age(25)
                .contact("7011567590")
                .build();
        this.mockMvc.perform(post("/player/signUp").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player1)));

        Player player2 = Player.builder()
                .playerName("Test1")
                .age(25)
                .contact("7011567590")
                .build();
        this.mockMvc.perform(post("/player/signUp").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player2)))
                .andDo(print()).andExpect(status().isConflict())
                .andExpect(jsonPath("$.message")
                        .value("Player with same contact details is already registered in the system"));
    }

    @Test
    void testNewUserSignup_returnException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Player player = Player.builder()
                .age(25)
                .build();
        this.mockMvc.perform(post("/player/signUp").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message")
                        .value(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
}
