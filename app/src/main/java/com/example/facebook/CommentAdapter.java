package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private final List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView content;
        TextView createdAt;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.comment_user);
            content = itemView.findViewById(R.id.comment_content);
            createdAt = itemView.findViewById(R.id.comment_time);
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.userName.setText(comment.getUserName());
        holder.content.setText(comment.getContent());
        holder.createdAt.setText(comment.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
