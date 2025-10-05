package com.example.facebook;

public class PostResponse {
    private boolean success;
    private String message;
    private PostData post; // đây là class bên trong PostResponse

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public PostData getPost() { return post; }

    // Class con đại diện dữ liệu server trả về
    public static class PostData {
        private String _id;
        private String userId;
        private String content;
        private String createdAt;
        private String videoId; // nếu server có trả về

        public String get_id() { return _id; }
        public String getUserId() { return userId; }
        public String getContent() { return content; }
        public String getCreatedAt() { return createdAt; }
        public String getVideoId() { return videoId; }
    }
}
