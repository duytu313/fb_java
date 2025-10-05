package com.example.facebook;

public class RegisterResponse {
    private boolean success;
    private String message;

    // Constructor
    public RegisterResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter và Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
