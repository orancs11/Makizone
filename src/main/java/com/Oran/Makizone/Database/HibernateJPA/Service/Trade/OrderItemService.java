package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;

import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private OrderItemRepo repo;

    @Autowired
    public OrderItemService(OrderItemRepo repo) {
        this.repo = repo;
    }

    public void saveItem(com.Oran.Makizone.Database.HibernateJPA.Model.Trade.OrderItem item) {
        repo.save(item);
    }
}
