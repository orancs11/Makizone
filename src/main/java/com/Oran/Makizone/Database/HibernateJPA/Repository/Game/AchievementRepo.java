package com.Oran.Makizone.Database.HibernateJPA.Repository.Game;

import com.Oran.Makizone.Database.HibernateJPA.Model.Game.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Long> {
}
