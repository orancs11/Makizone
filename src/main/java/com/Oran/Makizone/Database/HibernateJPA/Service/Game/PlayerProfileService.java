package com.Oran.Makizone.Database.HibernateJPA.Service.Game;


import com.Oran.Makizone.Database.HibernateJPA.Repository.Game.PlayerProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class PlayerProfileService {

    private PlayerProfileRepo repo;

    public PlayerProfileService(PlayerProfileRepo repo){
        this.repo = repo;
    }
}
