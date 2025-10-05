package com.example.facebook;

public class FeedPost {
    private String username;
    private int userAvatar;
    private String content;

    public FeedPost(String username, int userAvatar, String content) {
        this.username = username;
        this.userAvatar = userAvatar;
        this.content = content;
    }

    public String getUsername() { return username; }
    public int getUserAvatar() { return userAvatar; }
    public String getContent() { return content; }

    public void setUsername(String username) { this.username = username; }
    public void setUserAvatar(int userAvatar) { this.userAvatar = userAvatar; }
    public void setContent(String content) { this.content = content; }
}
