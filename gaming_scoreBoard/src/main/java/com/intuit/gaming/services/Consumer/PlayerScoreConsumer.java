package com.intuit.gaming.services.Consumer;

import com.google.gson.Gson;
import com.intuit.gaming.model.entity.PlayerScore;
import com.intuit.gaming.services.ScoreBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlayerScoreConsumer {

    @Autowired
    private ScoreBoardService scoreBoardService;

    Gson json = new Gson();

    @KafkaListener(topics = "ScoreTopic", groupId = "grp1")
    public void listenToKafkaTopic(String score) {

        log.info("Message consumed successfully {}", score);
        scoreBoardService.addPlayerScore(json.fromJson(score, PlayerScore.class));
    }
}
