package com.Oran.Makizone.Database.HibernateJPA.Model.Game;

import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(schema = "`Makizone-Game`", name = "player_profile")
public class PlayerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fame_points")
    private Integer famePoints;

    @Column(name = "health_points")
    private Integer healthPoints;

    @Column(name = "current_level")
    private Integer currentLevel;

    @Column(name = "active_title")
    private String activeTitle;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "badges")
    private Map<String, Object> badges;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "inventory")
    private Map<String, Integer> inventory;

    @OneToOne
    @JoinColumn(name = "user_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFamePoints() {
        return famePoints;
    }

    public void setFamePoints(Integer famePoints) {
        this.famePoints = famePoints;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Object> getBadges() {
        return badges;
    }

    public void setBadges(Map<String, Object> badges) {
        this.badges = badges;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PlayerProfile{" +
                "id=" + id +
                ", famePoints=" + famePoints +
                ", healthPoints=" + healthPoints +
                ", currentLevel=" + currentLevel +
                ", activeTitle='" + activeTitle + '\'' +
                ", updatedAt=" + updatedAt +
                ", badges=" + badges +
                ", user=" + user +
                '}';
    }
}
