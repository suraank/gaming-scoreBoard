package com.intuit.gaming.repository;

import com.intuit.gaming.model.entity.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, Long> {
}
