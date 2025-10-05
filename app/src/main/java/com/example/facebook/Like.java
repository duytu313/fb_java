package com.example.facebook;

public class Like {
    private String _id;
    private String postId;
    private String userId;
    private String createdAt;

    public Like(String _id, String postId, String userId, String createdAt) {
        this._id = _id;
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
