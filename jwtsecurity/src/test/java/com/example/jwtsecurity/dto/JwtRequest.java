package com.example.jwtsecurity.dto;

public class JwtRequest {
    private String username;
    private String password;

    // Getters and Setters
}

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
}
