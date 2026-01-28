package com.Oran.Makizone.Database.HibernateJPA.Model.Trade;


import com.Oran.Makizone.Utilities.Enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(schema="Makizone-Trade", name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_price")
    private BigDecimal totalPrice;
    @Column(name = "status")
    private OrderStatus status;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "shipping_address")
    private Map<String, String> shippingAddress;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Map<String, String> getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Map<String, String> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", shippingAddress=" + shippingAddress +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
