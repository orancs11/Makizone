package com.Oran.Makizone.Database.HibernateJPA.Repository.Trade;


import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
