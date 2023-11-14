package com.example.demo4.User;

public class AuthResult {
    private int userId;
    private boolean isAuthenticated;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AuthResult(int userId, boolean isAuthenticated, String role) {
        this.userId = userId;
        this.isAuthenticated = isAuthenticated;
        this.role=role;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}