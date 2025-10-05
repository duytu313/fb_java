package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    private final List<Friend> friends;
    private final OnAddClickListener onAddClick;
    private final OnItemClickListener onItemClick;

    // --- Interface callback ---
    public interface OnAddClickListener {
        void onAddClick(Friend friend);
    }

    public interface OnItemClickListener {
        void onItemClick(Friend friend);
    }

    // --- Constructor ---
    public FriendsAdapter(List<Friend> friends,
                          OnAddClickListener onAddClick,
                          OnItemClickListener onItemClick) {
        this.friends = friends;
        this.onAddClick = onAddClick;
        this.onItemClick = onItemClick;
    }

    // --- ViewHolder ---
    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView status;
        TextView btnAdd;

        public FriendViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.friendAvatar);
            name = itemView.findViewById(R.id.friendName);
            status = itemView.findViewById(R.id.friendStatus);
            btnAdd = itemView.findViewById(R.id.btnAddFriend);
        }
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.name.setText(friend.getName());
        holder.status.setText(friend.getStatus());

        // Load avatar (hoặc mặc định)
        if (friend.getAvatarRes() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(friend.getAvatarRes())
                    .circleCrop()
                    .into(holder.avatar);
        } else {
            holder.avatar.setImageResource(R.drawable.avatar1);
        }

        // Sự kiện click
        holder.btnAdd.setOnClickListener(v -> onAddClick.onAddClick(friend));
        holder.itemView.setOnClickListener(v -> onItemClick.onItemClick(friend));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
