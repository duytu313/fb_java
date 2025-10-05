package com.example.facebook;

import java.util.List;

public class CommentsResponse {
    private boolean success;
    private List<Comment> comments; // Cần tạo Comment.java

    public boolean isSuccess() { return success; }
    public List<Comment> getComments() { return comments; }
}
