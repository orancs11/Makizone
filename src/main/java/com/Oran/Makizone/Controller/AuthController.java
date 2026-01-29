package com.Oran.Makizone.Controller;


import com.Oran.Makizone.DTO.AuthResponse;
import com.Oran.Makizone.DTO.LoginRequest;
import com.Oran.Makizone.DTO.RegisterRequest;
import com.Oran.Makizone.Database.HibernateJPA.Service.Trade.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        userService.registerUser(registerRequest);
        return ResponseEntity.ok("User Registered Sucessfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        Map<String, String> userCredentials = this.userService.loginUser(loginRequest);
        return ResponseEntity.ok(new AuthResponse(userCredentials));
    }


}
