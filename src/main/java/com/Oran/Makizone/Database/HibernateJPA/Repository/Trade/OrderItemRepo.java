package com.Oran.Makizone.Database.HibernateJPA.Repository.Trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepo extends JpaRepository<OrderItemRepo, Long> {

}
