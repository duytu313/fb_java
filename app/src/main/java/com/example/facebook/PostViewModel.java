package com.example.facebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends ViewModel {

    private final MutableLiveData<List<Post>> _posts = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Post>> getPosts() {
        return _posts;
    }

    // ===== Thêm bài viết mới lên đầu =====
    public void addPost(Post post) {
        // Thêm local trước
        List<Post> updatedList = _posts.getValue();
        if (updatedList == null) updatedList = new ArrayList<>();
        updatedList.add(0, post);
        _posts.setValue(updatedList);

        // Gửi lên server
        RequestBody userIdBody = RequestBody.create(
                MediaType.parse("text/plain"),
                post.getUserId() != null ? post.getUserId() : ""
        );
        RequestBody contentBody = RequestBody.create(
                MediaType.parse("text/plain"),
                post.getContent() != null ? post.getContent() : ""
        );

        ApiClient.getApiService().createPost(userIdBody, contentBody, null)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getPost() != null) {
                            PostResponse.PostData serverPost = response.body().getPost();

                            Post updatedPost = new Post(
                                    serverPost.get_id() != null ? serverPost.get_id() : post.getId(),
                                    serverPost.getUserId() != null ? serverPost.getUserId() : post.getUserId(),
                                    post.getUserName(),                 // server không trả userName, giữ local
                                    serverPost.getContent() != null ? serverPost.getContent() : post.getContent(),
                                    null,                               // imageRes
                                    serverPost.getVideoId(),            // videoId
                                    serverPost.getCreatedAt() != null ? serverPost.getCreatedAt() : post.getCreatedAt(),
                                    false                               // mặc định false
                            );

                            List<Post> currentList = _posts.getValue();
                            if (currentList == null) currentList = new ArrayList<>();
                            currentList.set(0, updatedPost);
                            _posts.setValue(currentList);
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        // Log lỗi nếu muốn
                    }
                });
    }

    // ===== Cập nhật toàn bộ danh sách bài viết =====
    public void updatePosts(List<Post> newPosts) {
        _posts.setValue(new ArrayList<>(newPosts));
    }

    // ===== Load bài viết từ server =====
    public void loadPostsFromServer() {
        ApiClient.getApiService().getPosts().enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<PostsResponse.PostData> postsFromServer = response.body().getPosts();
                    List<Post> localPosts = new ArrayList<>();

                    if (postsFromServer != null) {
                        for (PostsResponse.PostData serverPost : postsFromServer) {
                            localPosts.add(new Post(
                                    serverPost.get_id(),
                                    serverPost.getUserId(),
                                    null,                               // userName nếu server không trả
                                    serverPost.getContent(),
                                    null,                               // imageRes
                                    serverPost.getVideoId(),            // videoId
                                    serverPost.getCreatedAt(),
                                    false
                            ));
                        }
                    }
                    _posts.setValue(localPosts);
                }
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                // Log lỗi nếu muốn
            }
        });
    }
}
