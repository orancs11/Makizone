package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;


import com.Oran.Makizone.DTO.LoginRequest;
import com.Oran.Makizone.DTO.RegisterRequest;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.UserRepo;
import com.Oran.Makizone.Utilities.Hasher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo repo;

    @Autowired
    public UserService(UserRepo repo){
        this.repo = repo;
    }

    @Transactional
    public boolean registerUser(RegisterRequest registerRequest){
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String fullName = registerRequest.getFullName();
        Map<String, String> address = registerRequest.getAddress();

        Optional<User> temp = this.repo.findByEmail(email);
        if(temp.isPresent()) return false;

        User user = new User();
        user.setEmail(email);
        user.setPassword(Hasher.hashed_password(password));
        user.setFullName(fullName);
        user.setAddress(address);
        this.repo.save(user);

        return true;
    }

    public boolean loginUser(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String hashedPassword = Hasher.hashed_password(loginRequest.getPassword());

        Optional<User> temp = this.repo.findByEmail(email);
        if(temp.isPresent()){
            User tempUser = temp.get();
            return tempUser.getEmail().equals(email) && tempUser.getPassword().equals(hashedPassword);
        }
        return false;
    }





}
