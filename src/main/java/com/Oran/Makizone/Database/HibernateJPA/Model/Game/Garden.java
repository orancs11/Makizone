package com.Oran.Makizone.Database.HibernateJPA.Model.Game;

import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Utilities.GardenTheme;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(schema = "Makizone-Game", name = "garden")
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme")
    private GardenTheme theme;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "layout_data")
    private Map<String,Object> layoutData;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GardenTheme getTheme() {
        return theme;
    }

    public void setTheme(GardenTheme theme) {
        this.theme = theme;
    }

    public Map<String, Object> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(Map<String, Object> layoutData) {
        this.layoutData = layoutData;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Garden{" +
                "id=" + id +
                ", theme=" + theme +
                ", layoutData=" + layoutData +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }
}
