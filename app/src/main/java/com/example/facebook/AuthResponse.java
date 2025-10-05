package com.example.facebook;

public class AuthResponse {
    private boolean success;
    private String message;
    private String userId;
    private String token;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getUserId() { return userId; }
    public String getToken() { return token; }
}
