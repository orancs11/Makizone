package com.Oran.Makizone.Database.HibernateJPA.Repository.Game;

import com.Oran.Makizone.Database.HibernateJPA.Model.Game.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import java.util.Optional;

@Repository
public interface PlayerProfileRepo extends JpaRepository<PlayerProfile, Long> {
    Optional<PlayerProfile> findByUser(User user);
}
