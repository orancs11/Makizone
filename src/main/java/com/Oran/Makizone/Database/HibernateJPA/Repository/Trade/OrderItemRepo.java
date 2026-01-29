package com.Oran.Makizone.Database.HibernateJPA.Repository.Trade;

import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
