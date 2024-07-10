package com.intuit.gaming.model.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private Integer age;

}