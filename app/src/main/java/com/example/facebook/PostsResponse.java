package com.example.facebook;

import java.util.List;

public class PostsResponse {
    private boolean success;
    private String message; // thêm message từ server
    private List<PostData> posts;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<PostData> getPosts() { return posts; }

    // Class con đại diện mỗi post
    public static class PostData {
        private String _id;
        private String userId;
        private String content;
        private String createdAt;
        private String videoId;
        private String imageUrl; // nếu sau này muốn load ảnh từ server

        public String get_id() { return _id; }
        public String getUserId() { return userId; }
        public String getContent() { return content; }
        public String getCreatedAt() { return createdAt; }
        public String getVideoId() { return videoId; }
        public String getImageUrl() { return imageUrl; }
    }
}
