package com.intuit.gaming.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "score")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScore implements Comparable<PlayerScore>{

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "player_id")
    @Column(nullable = false)
    private Long playerId;

    @Column(nullable = false)
    private Integer score;

    @Override
    public int compareTo(PlayerScore o) {

        return Integer.compare(this.score, o.score);

    }
}
