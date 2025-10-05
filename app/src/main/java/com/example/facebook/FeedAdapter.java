package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// ================== ADAPTER ==================
class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private final List<FeedPost> posts;

    public FeedAdapter(List<FeedPost> posts) {
        this.posts = posts;
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView ivAvatar;
        TextView tvContent;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed_post, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedPost post = posts.get(position);
        holder.tvUsername.setText(post.getUsername());
        holder.ivAvatar.setImageResource(post.getUserAvatar());
        holder.tvContent.setText(post.getContent());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
