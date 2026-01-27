package com.Oran.Makizone.Database.HibernateJPA.Service.Game;


import com.Oran.Makizone.Database.HibernateJPA.Repository.Game.AchievementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
    private AchievementRepo repo;

    @Autowired
    public AchievementService(AchievementRepo repo){
        this.repo = repo;
    }
}
