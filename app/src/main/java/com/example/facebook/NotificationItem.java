package com.example.facebook;

public class NotificationItem {

    private String username;
    private String message;
    private int avatarRes;   // ảnh đại diện (resource ID)
    private String time;     // ví dụ: "2 giờ trước"

    public NotificationItem(String username, String message, int avatarRes, String time) {
        this.username = username;
        this.message = message;
        this.avatarRes = avatarRes;
        this.time = time;
    }

    // --- Getters ---
    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public int getAvatarRes() {
        return avatarRes;
    }

    public String getTime() {
        return time;
    }

    // --- Setters (nếu cần sửa dữ liệu sau này) ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAvatarRes(int avatarRes) {
        this.avatarRes = avatarRes;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
