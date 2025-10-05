package com.example.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFriends);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Danh sách bạn bè mẫu
        List<Friend> friendsList = Arrays.asList(
                new Friend("1", "Nguyễn Văn A", R.drawable.avatar1, "Online"),
                new Friend("2", "Trần Thị B", R.drawable.avatar2, "Offline"),
                new Friend("3", "Lê Văn C", R.drawable.avatar3, "Online"),
                new Friend("4", "Phạm Thị D", R.drawable.avatar4, "Offline")
        );

        // Adapter với callback click
        FriendsAdapter adapter = new FriendsAdapter(
                friendsList,
                friend -> {
                    Toast.makeText(
                            this,
                            "Đã gửi lời mời kết bạn tới " + friend.getName(),
                            Toast.LENGTH_SHORT
                    ).show();
                },
                friend -> {
                    Intent intent = new Intent(this, FriendDetailActivity.class);
                    intent.putExtra("friend_data", friend);
                    startActivity(intent);
                }
        );

        recyclerView.setAdapter(adapter);
    }
}
