package com.intuit.gaming.services;

import com.intuit.gaming.exception.PlayerException;
import com.intuit.gaming.model.dto.PlayerScoreDto;
import com.intuit.gaming.model.entity.PlayerScore;

import java.util.List;

public interface ScoreBoardService {

    PlayerScore addPlayerScore(PlayerScore score);

    void publishScoreMessageToTopic(PlayerScore score) throws PlayerException;
    List<PlayerScoreDto> getTopNScores(int n);
    List<PlayerScoreDto> getTopFiveScores();
}
