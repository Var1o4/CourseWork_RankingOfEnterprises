package com.example.demo4.User;

public class AuthResult {
    private int userId;
    private boolean isAuthenticated;

    public AuthResult(int userId, boolean isAuthenticated) {
        this.userId = userId;
        this.isAuthenticated = isAuthenticated;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}