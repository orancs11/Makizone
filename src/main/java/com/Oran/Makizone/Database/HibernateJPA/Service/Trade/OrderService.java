package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;


import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepo repo;
    @Autowired
    public OrderService(OrderRepo repo){
        this.repo = repo;
    }



}
