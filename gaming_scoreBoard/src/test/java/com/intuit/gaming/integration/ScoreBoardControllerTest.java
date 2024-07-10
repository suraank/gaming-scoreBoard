package com.intuit.gaming.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.gaming.model.entity.Player;
import com.intuit.gaming.model.entity.PlayerScore;
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
public class ScoreBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddPlayerScore_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Player player = Player.builder()
                .playerName("Test")
                .age(25)
                .contact("7011567594")
                .build();
        this.mockMvc.perform(post("/player/signUp").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));

        PlayerScore playerScore = PlayerScore.builder()
                .playerId(1L)
                .score(10)
                .build();
        this.mockMvc.perform(post("/score/addScore").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerScore)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));
    }

    @Test
    void testAddPlayerScore_returnNotFoundException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PlayerScore playerScore = PlayerScore.builder()
                .playerId(2L)
                .score(10)
                .build();
        this.mockMvc.perform(post("/score/addScore").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerScore)))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("Player is not registered in the system"));
    }
}
