package com.intuit.gaming.services.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.intuit.gaming.constants.ErrorMessage;
import com.intuit.gaming.exception.PlayerException;
import com.intuit.gaming.model.entity.Player;
import com.intuit.gaming.repository.PlayerRepository;
import com.intuit.gaming.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public Player signUpPlayer(Player player) throws PlayerException, IllegalArgumentException, BadRequestException {
        try {
            if (Objects.nonNull(playerRepository.findByContact(player.getContact()))) {
                throw new PlayerException(ErrorMessage.CONFLICT_EXCEPTION_MESSAGE);
            }

            if(player.getPlayerName() == null || player.getContact() == null) {
                throw new BadRequestException(ErrorMessage.BAD_REQUEST_EXCEPTION_MESSAGE);
            }

            if(!isPhoneNumberValid(player.getContact())) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INFORMATION_EXCEPTION_MESSAGE);
            }

            playerRepository.save(player);
            return player;
        } catch (PlayerException e) {
            throw new PlayerException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private boolean isPhoneNumberValid(String phone)
    {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;

        try {
            phoneNumber = phoneUtil.parse(phone, "IN");
        }
        catch (NumberParseException e) {
            log.error("Exception while parsing the contact no");
            throw new RuntimeException(e);
        }
        return phoneUtil.isValidNumber(phoneNumber);
    }
}
