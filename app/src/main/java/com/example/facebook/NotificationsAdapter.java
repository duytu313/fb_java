package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private final List<NotificationItem> notifications;

    public NotificationsAdapter(List<NotificationItem> notifications) {
        this.notifications = notifications;
    }

    // --- ViewHolder ---
    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvUsername, tvMessage, tvTime;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationItem item = notifications.get(position);
        holder.ivAvatar.setImageResource(item.getAvatarRes());
        holder.tvUsername.setText(item.getUsername());
        holder.tvMessage.setText(item.getMessage());
        holder.tvTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
