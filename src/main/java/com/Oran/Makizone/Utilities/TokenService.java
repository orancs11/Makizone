package com.Oran.Makizone.Utilities;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenService {

    private static final String SECRET_KEY = GVF.SECRET_KEY_TS;
    private static final long EXPIRATION_TIME = GVF.EXPIRATION_TIME;

    public String generateToken(String email){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isTokenValid(String token, String email){
        final String extractedMail = extractMail(token);
        return (extractedMail.equals(email) && !isTokenExpired(token));
    }

    public String extractMail(String token){
        if(token == null || token.isEmpty()) return null;
        String[] parts = token.split(" ");
        String part = (parts.length == 2) ? parts[1] : token;
        return extractClaim(part, Claims::getSubject);
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(GVF.SECRET_KEY_TS);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
