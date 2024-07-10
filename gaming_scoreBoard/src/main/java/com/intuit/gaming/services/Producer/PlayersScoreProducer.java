package com.intuit.gaming.services.Producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlayersScoreProducer implements KafkaMessageProducer{

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessageToTopic(String score) {

        kafkaTemplate.send("ScoreTopic", score);
        log.info("Player's score has been published to topic{}",score);
    }
}
