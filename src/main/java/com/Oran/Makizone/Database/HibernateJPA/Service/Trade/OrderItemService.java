package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;


import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.OrderItemRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private OrderItemRepo repo;

    public OrderItemService(OrderItemRepo repo){
        this.repo = repo;
    }

}
