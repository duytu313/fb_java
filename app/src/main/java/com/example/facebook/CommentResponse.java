package com.example.facebook;

public class CommentResponse {
    private boolean success;
    private String message;
    private Comment comment; // Bạn cần tạo class Comment.java

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Comment getComment() { return comment; }
}
