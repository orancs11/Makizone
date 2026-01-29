package com.Oran.Makizone.Database.HibernateJPA.Service.Game;


import com.Oran.Makizone.Database.HibernateJPA.Model.Game.Garden;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Game.GardenRepo;
import com.Oran.Makizone.Utilities.Enums.GardenTheme;
import com.Oran.Makizone.Utilities.GVF;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GardenService {
    private GardenRepo repo;

    @Autowired
    public GardenService(GardenRepo repo){
        this.repo = repo;
    }


    @Transactional
    public void saveGarden(User user){
        Garden currGarden = new Garden();
        Map<String, Object> layout = GVF.createInitialLayout();
        currGarden.setLayoutData(layout);
        currGarden.setTheme(GardenTheme.DEFAULT_FARM);
        currGarden.setUser(user);
        repo.save(currGarden);
    }


    public Garden findGarden(User user){
        Optional<Garden> list = this.repo.findByUser(user);
        if(list.isEmpty()) return null;
        return list.get();
    }

    public void updateGarden(Garden garden){
        this.repo.save(garden);
    }

}
