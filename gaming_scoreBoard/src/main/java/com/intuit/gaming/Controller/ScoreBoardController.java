package com.intuit.gaming.Controller;

import com.google.gson.Gson;
import com.intuit.gaming.exception.PlayerException;
import com.intuit.gaming.factory.CustomResponseEntityFactory;
import com.intuit.gaming.model.dto.PlayerScoreDto;
import com.intuit.gaming.model.entity.CustomResponseEntity;
import com.intuit.gaming.model.entity.PlayerScore;
import com.intuit.gaming.repository.PlayerRepository;
import com.intuit.gaming.repository.PlayerScoreRepository;
import com.intuit.gaming.services.ScoreBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/score")
public class ScoreBoardController {

    @Autowired
    private ScoreBoardService scoreBoardService;

    @Autowired
    private CustomResponseEntityFactory customResponseEntityFactory;

    @PostMapping("/addScore")
    public ResponseEntity publishScore(@RequestBody PlayerScore score) {

        try {
            scoreBoardService.publishScoreMessageToTopic(score);
            return ResponseEntity.status(HttpStatus.OK).body(customResponseEntityFactory.getSuccessResponse(score));
        } catch (PlayerException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponseEntityFactory.getNotFoundResponse(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponseEntityFactory.getISEResponse());
        }
    }

    @GetMapping("/getTopScorers")
    public List<PlayerScoreDto> getTopScorers(int top) {
        return scoreBoardService.getTopNScores(top);
    }

    @GetMapping("/getTopFiveScorers")
    public List<PlayerScoreDto> getTopFiveScorers() {
        return scoreBoardService.getTopFiveScores();
    }
}