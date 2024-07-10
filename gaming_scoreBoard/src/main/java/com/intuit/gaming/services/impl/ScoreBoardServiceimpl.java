package com.intuit.gaming.services.impl;

import com.google.gson.Gson;
import com.intuit.gaming.exception.PlayerException;
import com.intuit.gaming.model.dto.PlayerScoreDto;
import com.intuit.gaming.model.entity.Player;
import com.intuit.gaming.model.entity.PlayerScore;
import com.intuit.gaming.repository.PlayerRepository;
import com.intuit.gaming.repository.PlayerScoreRepository;
import com.intuit.gaming.services.Producer.PlayersScoreProducer;
import com.intuit.gaming.services.ScoreBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

@Slf4j
@Service
public class ScoreBoardServiceimpl implements ScoreBoardService {

    @Autowired
    private PlayerScoreRepository playerScoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayersScoreProducer playersScoreProducer;
    @Override
    public PlayerScore addPlayerScore(PlayerScore score) {
        playerScoreRepository.save(score);
        return score;
    }

    Gson gson = new Gson();
    @Override
    public void publishScoreMessageToTopic(PlayerScore score) throws PlayerException {
        try {
            Optional<Player> player = playerRepository.findById(score.getPlayerId());
            if(player.isEmpty()) {
                log.info("Player is not registered in the system");
                throw new PlayerException("Player is not registered in the system");
            }
        } catch (PlayerException e) {
            throw new PlayerException(e.getMessage());
        }
        playersScoreProducer.sendMessageToTopic(gson.toJson(score));
    }

    @Override
    public List<PlayerScoreDto> getTopNScores(int top) {
        List<PlayerScore> allScores = playerScoreRepository.findAll();
        PriorityQueue<PlayerScore> topScores = new PriorityQueue<>();
        for(PlayerScore score : allScores) {
            if(topScores.size() < top) {
                topScores.add(score);
                continue;
            }
            if(score.getScore() > topScores.peek().getScore()) {
                topScores.poll();
                topScores.add(score);
            }
        }
        List<PlayerScore> topNScores = new ArrayList<>();
        topNScores.addAll(topScores);
        log.info("Top {} scores are : {}",top, topNScores);

        List<PlayerScoreDto> playerScoreDtoList = new ArrayList<>();
        for(PlayerScore score : topNScores) {
            Optional<Player> playerOptional = playerRepository.findById(score.getPlayerId());
            PlayerScoreDto playerScoreDto = new PlayerScoreDto();
            playerScoreDto.setScore(score.getScore());
            playerScoreDto.setPlayerName(playerOptional.get().getPlayerName());
            playerScoreDtoList.add(playerScoreDto);
        }
        return playerScoreDtoList;
    }

    @Override
    public List<PlayerScoreDto> getTopFiveScores() {
        return null;
    }
}
