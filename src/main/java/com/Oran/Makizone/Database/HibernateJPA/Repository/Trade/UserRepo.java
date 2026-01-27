package com.Oran.Makizone.Database.HibernateJPA.Repository.Trade;


import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
