package com.example.facebook;

public class Chat {

    private int avatarResId;
    private String name;
    private String message;
    private String time;
    private boolean isOnline;

    // Constructor
    public Chat(int avatarResId, String name, String message, String time, boolean isOnline) {
        this.avatarResId = avatarResId;
        this.name = name;
        this.message = message;
        this.time = time;
        this.isOnline = isOnline;
    }

    // Getters & Setters
    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(int avatarResId) {
        this.avatarResId = avatarResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
