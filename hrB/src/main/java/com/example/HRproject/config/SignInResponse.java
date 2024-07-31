package com.example.HRproject.config;

public class SignInResponse {
    private boolean authenticated;
    private String role;

    public SignInResponse(boolean authenticated, String role) {
        this.authenticated = authenticated;
        this.role = role;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getRole() {
        return role;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setRole(String role) {
        this.role = role;
    }
}