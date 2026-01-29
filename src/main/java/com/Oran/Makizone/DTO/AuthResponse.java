package com.Oran.Makizone.DTO;

import java.util.Map;

public class AuthResponse {
    private String token;
    private String fullName;

    public AuthResponse(String token){
        this.token = token;
    }

    public AuthResponse(Map<String, String> credentials){
        this.token = credentials.get("token");
        this.fullName = credentials.get("fullName");
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
