package com.Oran.Makizone.Database.HibernateJPA.Model.Game;


import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "Makizone-Game", name = "achievement")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "achievement_code", nullable = false)
    private String achievementCode;

    @Column(name = "current_progress", nullable = false)
    private Integer currentProgres;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAchievementCode() {
        return achievementCode;
    }

    public void setAchievementCode(String achievementCode) {
        this.achievementCode = achievementCode;
    }

    public Integer getCurrentProgres() {
        return currentProgres;
    }

    public void setCurrentProgres(Integer currentProgres) {
        this.currentProgres = currentProgres;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", achievementCode='" + achievementCode + '\'' +
                ", currentProgres=" + currentProgres +
                ", isCompleted=" + isCompleted +
                ", completedAt=" + completedAt +
                ", user=" + user +
                '}';
    }
}
