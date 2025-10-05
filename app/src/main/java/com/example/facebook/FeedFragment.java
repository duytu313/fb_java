package com.example.facebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private final List<Post> postList = new ArrayList<>();
    private final String currentUserId = "YOUR_USER_ID"; // TODO: lấy từ login hoặc SharedPreferences

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        postAdapter = new PostAdapter(postList, currentUserId, post -> {
            if (post.getId() != null) {
                CommentFragment fragment = CommentFragment.newInstance(post.getId());
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();

                View header = requireActivity().findViewById(R.id.header);
                if (header != null) header.setVisibility(View.GONE);
            }
        });

        recyclerView.setAdapter(postAdapter);
        loadPostsFromServer();

        return view;
    }

    private void loadPostsFromServer() {
        ApiClient.getApiService().getPosts().enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(@NonNull Call<PostsResponse> call, @NonNull Response<PostsResponse> response) {
                PostsResponse body = response.body();
                if (response.isSuccessful() && body != null && body.isSuccess()) {
                    List<Post> posts = new ArrayList<>();
                    if (body.getPosts() != null) {
                        for (PostsResponse.PostData serverPost : body.getPosts()) {
                            posts.add(new Post(
                                    serverPost.get_id() != null ? serverPost.get_id() : String.valueOf(System.currentTimeMillis()),
                                    serverPost.getUserId(),
                                    "Người dùng", // server không trả userName, dùng default
                                    serverPost.getContent() != null ? serverPost.getContent() : "",
                                    null, // imageRes, nếu muốn dùng serverPost.getImageUrl() thì cần convert
                                    serverPost.getVideoId() != null ? serverPost.getVideoId() : null, // videoRes kiểu String
                                    serverPost.getCreatedAt() != null ? serverPost.getCreatedAt() : "Vừa xong",
                                    false
                            ));
                        }
                    }

                    postList.clear();
                    postList.addAll(posts);
                    postAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(requireContext(), "Lỗi tải bài viết", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostsResponse> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void likePost(Post post) {
        String postId = post.getId();
        if (postId == null) return;

        ApiClient.getApiService().likePost(postId, currentUserId)
                .enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            post.setLiked(!post.isLiked());
                            postAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(requireContext(), "Like thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LikeResponse> call, @NonNull Throwable t) {
                        Toast.makeText(requireContext(), "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addPostOnTop(Post post) {
        postList.add(0, post);
        postAdapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }
}
