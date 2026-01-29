package com.Oran.Makizone.DTO;

import java.util.Map;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private Map<String, Object> address;

    public RegisterRequest(String email, String password, String fullName, Map<String, Object> address){
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<String, Object> getAddress() {
        return address;
    }

}
