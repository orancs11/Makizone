package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;


import com.Oran.Makizone.DTO.LoginRequest;
import com.Oran.Makizone.DTO.RegisterRequest;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.UserRepo;
import com.Oran.Makizone.Utilities.Exceptions.InvalidCredentialsException;
import com.Oran.Makizone.Utilities.Exceptions.UserAlreadyExistException;
import com.Oran.Makizone.Utilities.Exceptions.UserNotFoundException;
import com.Oran.Makizone.Utilities.Hasher;
import com.Oran.Makizone.Utilities.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    private UserRepo repo;
    private TokenService tokenService;
    @Autowired
    public UserService(UserRepo repo, TokenService tokenService)
    {
        this.repo = repo;
        this.tokenService = tokenService;
    }

    @Transactional
    public void registerUser(RegisterRequest registerRequest) throws RuntimeException{
        String email = registerRequest.getEmail();
        if(email.isEmpty() || !email.contains("@")) throw new InvalidCredentialsException("UserService.registerUser - EMAIL CAN'T BE EMPTY AND MUST CONTAIN @");

        String password = registerRequest.getPassword();
        if(password.isEmpty()) throw new InvalidCredentialsException("UserService.registerUser - PASSWORD CAN'T BE EMPTY");
        String fullName = registerRequest.getFullName();
        if(fullName.isEmpty()) throw new InvalidCredentialsException("UserService.registerUser - NAME CAN'T BE EMPTY");
        Map<String, Object> address = registerRequest.getAddress();
        if(address.isEmpty()) throw new InvalidCredentialsException("userService.registerUser - ADDRESS MUST BE FILLED");

        Optional<User> temp = this.repo.findByEmail(email);
        if(temp.isPresent()) throw new UserAlreadyExistException("");

        User user = new User();
        user.setEmail(email);
        user.setPassword(Hasher.hashPassword(password));
        user.setFullName(fullName);
        user.setAddress(address);
        List<String> roles = new ArrayList<>();

        roles.add("CUSTOMER");
        user.setRole(roles);

        this.repo.save(user);
        System.out.println("UserService.registerUser - USER SUCCESSFULLY ADDED TO DATABASE!");

    }

    public Map<String, String> loginUser(LoginRequest loginRequest){
        Map<String, String> result = new HashMap<>();
        String email = loginRequest.getEmail();
        if(!email.contains("@")) throw new InvalidCredentialsException("UserService.loginUser - EMAIL CAN'T BE EMPTY AND MUST CONTAIN @");
        String password = loginRequest.getPassword();
        if(password.isEmpty()) throw new InvalidCredentialsException("UserService.loginUser - PASSWORD CAN'T BE EMPTY");
        User currUser = checkUserExist(email, password);
        if(currUser == null) throw new UserNotFoundException("UserService.loginUser - USER DOES NOT EXIST!");
        result.put("token", tokenService.generateToken(email));
        result.put("fullName", currUser.getFullName());
        return result;
    }



    private User checkUserExist(String email, String password){
        Optional<User> list= this.repo.findByEmail(email);
        if(!list.isPresent()) return null;
        User user = list.get();
        System.out.println(user);
        if(!Hasher.verifyPassword(password, user.getPassword())) return null;
        return user;
    }





}
