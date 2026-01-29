package com.Oran.Makizone.Controller;

import com.Oran.Makizone.Database.HibernateJPA.Model.Game.PlayerProfile;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Service.Game.PlayerProfileService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Trade.UserService;
import com.Oran.Makizone.Utilities.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final TokenService tokenService;
    private final UserService userService;
    private final PlayerProfileService playerProfileService;

    @Autowired
    public ProfileController(TokenService tokenService, UserService userService,
            PlayerProfileService playerProfileService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.playerProfileService = playerProfileService;
    }

    @GetMapping
    public ResponseEntity<PlayerProfile> getMyProfile(@RequestHeader("Authorization") String token) {
        String email = tokenService.extractMail(token);
        User user = userService.findUser(email);
        PlayerProfile profile = playerProfileService.findProfile(user);
        return ResponseEntity.ok(profile);
    }
}
