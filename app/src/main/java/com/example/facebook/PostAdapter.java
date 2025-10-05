package com.example.facebook;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final List<Post> posts = new ArrayList<>();
    private final String currentUserId;
    private final OnCommentClickListener onCommentClick;

    public interface OnCommentClickListener {
        void onCommentClick(Post post);
    }

    public PostAdapter(List<Post> posts, String currentUserId, OnCommentClickListener onCommentClick) {
        if (posts != null) this.posts.addAll(posts);
        this.currentUserId = currentUserId;
        this.onCommentClick = onCommentClick;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView userName, content, btnLike, btnComment, btnShare;
        ImageView postImage;
        YouTubePlayerView youtubePlayerView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.post_user);
            content = itemView.findViewById(R.id.post_content);
            postImage = itemView.findViewById(R.id.post_image);
            youtubePlayerView = itemView.findViewById(R.id.youtube_player_view);
            btnLike = itemView.findViewById(R.id.btnLikeText);
            btnComment = itemView.findViewById(R.id.btnCommentText);
            btnShare = itemView.findViewById(R.id.btnShareText);
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.userName.setText(post.getUserName());
        holder.content.setText(post.getContent());

        // Load ảnh
        if (post.getImageRes() != null) {
            holder.postImage.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext())
                    .load(post.getImageRes())
                    .into(holder.postImage);
        } else holder.postImage.setVisibility(View.GONE);

        // Load video YouTube nếu có
        if (post.getVideoId() != null && !post.getVideoId().isEmpty()) {
            holder.youtubePlayerView.setVisibility(View.VISIBLE);
            holder.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.cueVideo(post.getVideoId(), 0f);
                }
            });
        } else holder.youtubePlayerView.setVisibility(View.GONE);

        // Like button
        updateLikeButton(holder, post);
        holder.btnLike.setOnClickListener(v -> toggleLike(post, holder));

        // Comment button
        holder.btnComment.setOnClickListener(v -> onCommentClick.onCommentClick(post));

        // Share button
        holder.btnShare.setOnClickListener(v ->
                Toast.makeText(holder.itemView.getContext(), "Share clicked", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Public methods
    public void updatePosts(List<Post> newPosts) {
        posts.clear();
        if (newPosts != null) posts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public void addPostOnTop(Post post) {
        posts.add(0, post);
        notifyItemInserted(0);
    }

    // Private helpers
    private void updateLikeButton(PostViewHolder holder, Post post) {
        holder.btnLike.setTextColor(post.isLiked() ? Color.BLUE : Color.GRAY);
    }

    private void toggleLike(Post post, PostViewHolder holder) {
        String postId = post.getId();
        if (postId == null) return;

        ApiClient.getApiService().likePost(postId, currentUserId)
                .enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            post.setLiked(true);
                            updateLikeButton(holder, post);
                            Toast.makeText(holder.itemView.getContext(), "Đã like", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(holder.itemView.getContext(), "Like thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LikeResponse> call, @NonNull Throwable t) {
                        Toast.makeText(holder.itemView.getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
