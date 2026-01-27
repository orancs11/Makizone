package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;


import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo repo;

    @Autowired
    public UserService(UserRepo repo){
        this.repo = repo;
    }


}
