package com.intuit.gaming.Controller;

import com.intuit.gaming.exception.PlayerException;
import com.intuit.gaming.factory.CustomResponseEntityFactory;
import com.intuit.gaming.model.entity.CustomResponseEntity;
import com.intuit.gaming.model.entity.Player;
import com.intuit.gaming.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/player")
public class PlayerSignUpController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CustomResponseEntityFactory customResponseEntityFactory;

    @PostMapping ("/signUp")
    public CustomResponseEntity playerSignUp(@RequestBody Player player) {

        try {
            Player playerToBeCreated = playerService.signUpPlayer(player);
            log.info("Player {} has been registered successfully",playerToBeCreated);
            return customResponseEntityFactory.getCreatedResponse(playerToBeCreated);
        } catch (PlayerException e) {
            log.error(e.getMessage());
            return customResponseEntityFactory.getConflictResponse(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return customResponseEntityFactory.getBadRequestResponse(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return customResponseEntityFactory.getISEResponse();
        }
    }
}
