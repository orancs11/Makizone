package com.Oran.Makizone.Database.HibernateJPA.Service.Game;

import com.Oran.Makizone.Database.HibernateJPA.Model.Game.PlayerProfile;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Game.PlayerProfileRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerProfileService {

    @org.springframework.beans.factory.annotation.Autowired
    private PlayerProfileRepo repo;

    public PlayerProfile findProfile(User user) {
        return repo.findByUser(user).orElseGet(() -> {
            PlayerProfile newProfile = new PlayerProfile();
            newProfile.setUser(user);
            newProfile.setFamePoints(0);
            newProfile.setHealthPoints(0);
            newProfile.setCurrentLevel(1);
            newProfile.setActiveTitle("Novice Gardener");
            newProfile.setInventory(new HashMap<>());
            newProfile.setBadges(new HashMap<>());
            newProfile.setUpdatedAt(LocalDateTime.now());
            return repo.save(newProfile);
        });
    }

    @Transactional
    public void addItemToInventory(User user, String itemId, int quantity) {
        PlayerProfile profile = findProfile(user);
        Map<String, Integer> inventory = profile.getInventory();
        if (inventory == null) {
            inventory = new HashMap<>();
        }
        inventory.put(itemId, inventory.getOrDefault(itemId, 0) + quantity);
        profile.setInventory(inventory);
        profile.setUpdatedAt(LocalDateTime.now());
        repo.save(profile);
    }

    @Transactional
    public boolean removeItemFromInventory(User user, String itemId, int quantity) {
        PlayerProfile profile = findProfile(user);
        Map<String, Integer> inventory = profile.getInventory();
        if (inventory == null || !inventory.containsKey(itemId)) {
            return false;
        }
        int currentQty = inventory.get(itemId);
        if (currentQty < quantity) {
            return false;
        }
        inventory.put(itemId, currentQty - quantity);
        if (inventory.get(itemId) == 0) {
            inventory.remove(itemId);
        }
        profile.setInventory(inventory);
        profile.setUpdatedAt(LocalDateTime.now());
        repo.save(profile);
        return true;
    }

    @Transactional
    public void addStats(User user, int health, int fame) {
        PlayerProfile profile = findProfile(user);
        profile.setHealthPoints(profile.getHealthPoints() + health);
        profile.setFamePoints(profile.getFamePoints() + fame);
        profile.setUpdatedAt(LocalDateTime.now());
        repo.save(profile);
    }
}
