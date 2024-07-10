
package com.intuit.gaming.repository;

import com.intuit.gaming.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByContact(String phoneNo);
}
