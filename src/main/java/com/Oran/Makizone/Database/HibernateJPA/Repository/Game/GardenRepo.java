package com.Oran.Makizone.Database.HibernateJPA.Repository.Game;


import com.Oran.Makizone.Database.HibernateJPA.Model.Game.Garden;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GardenRepo extends JpaRepository<Garden, Long> {
    public Optional<Garden> findByUser(User user);
}
