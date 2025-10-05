package com.example.facebook;

public class Comment {
    private String id;
    private String postId;
    private String userName;
    private String userId;
    private String content;
    private String createdAt;

    public Comment(String id, String postId, String userName, String userId, String content, String createdAt) {
        this.id = id;
        this.postId = postId;
        this.userName = userName;
        this.userId = userId != null ? userId : "";
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Setters (nếu cần chỉnh sửa giá trị sau khi tạo)
    public void setId(String id) {
        this.id = id;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
