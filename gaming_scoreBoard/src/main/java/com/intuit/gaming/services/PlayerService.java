
package com.intuit.gaming.services;

import com.intuit.gaming.model.entity.Player;
import org.springframework.stereotype.Component;

@Component
public interface PlayerService {

    Player signUpPlayer(Player player) throws Exception;

}
