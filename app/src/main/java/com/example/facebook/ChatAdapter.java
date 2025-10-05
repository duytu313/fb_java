package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final List<Chat> chats;

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView message;
        TextView time;
        View online;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.chat_avatar);
            name = itemView.findViewById(R.id.chat_user_name);
            message = itemView.findViewById(R.id.chat_message);
            time = itemView.findViewById(R.id.chat_time);
            online = itemView.findViewById(R.id.chat_onlineIndicator);
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.avatar.setImageResource(chat.getAvatarResId());
        holder.name.setText(chat.getName());
        holder.message.setText(chat.getMessage());
        holder.time.setText(chat.getTime());
        holder.online.setVisibility(chat.isOnline() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
