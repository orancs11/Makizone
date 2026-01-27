package com.Oran.Makizone.Database.HibernateJPA.Service.Game;


import com.Oran.Makizone.Database.HibernateJPA.Repository.Game.GardenRepo;
import org.springframework.stereotype.Service;

@Service
public class GardenService {
    private GardenRepo repo;

    public GardenService(GardenRepo repo){
        this.repo = repo;
    }
}
