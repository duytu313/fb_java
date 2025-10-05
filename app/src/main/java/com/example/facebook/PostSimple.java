package com.example.facebook;

public class PostSimple {

    private String userName;
    private String content;
    private int imageResId;

    public PostSimple(String userName, String content, int imageResId) {
        this.userName = userName;
        this.content = content;
        this.imageResId = imageResId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
