package com.example.facebook;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private static final List<Post> posts = new ArrayList<>();

    private PostRepository() {
        // Private constructor để ngăn tạo instance
    }

    // Thêm bài viết mới lên đầu danh sách
    public static void addPost(Post post) {
        posts.add(0, post);
    }

    // Lấy danh sách bài viết
    public static List<Post> getPosts() {
        return posts;
    }
}
