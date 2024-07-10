package com.intuit.gaming.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlayerScoreDto {
    private String playerName;
    private Integer score;
}
