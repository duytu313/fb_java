package com.example.facebook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MessengerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        // --- Sample data ---
        List<Story> storyList = Arrays.asList(
                new Story(R.drawable.avatar1, "Cường", true),
                new Story(R.drawable.avatar2, "Bùi", true),
                new Story(R.drawable.avatar3, "Chinh", false),
                new Story(R.drawable.avatar4, "Quỳnh", true),
                new Story(R.drawable.avatar5, "Hoàng", false),
                new Story(R.drawable.avatar6, "Princess👑❤️", true)
        );

        List<Chat> chatList = Arrays.asList(
                new Chat(R.drawable.avatar5, "Hoàng", "Đoạn chat đã được gửi...", "17:21", true),
                new Chat(R.drawable.avatar6, "Princess👑❤️", "Đoạn chat đã được gửi...", "17:20", true),
                new Chat(R.drawable.avatar1, "Cường", "Hello!", "16:45", false)
        );

        // --- RecyclerViews ---
        RecyclerView storyRecyclerView = findViewById(R.id.storyRecyclerView);
        RecyclerView chatRecyclerView = findViewById(R.id.chatRecyclerView);

        storyRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        storyRecyclerView.setAdapter(new StoryAdapter(storyList));

        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(new ChatAdapter(chatList));
    }
}
