package com.example.facebook;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FriendDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        ImageView avatar = findViewById(R.id.friendDetailAvatar);
        TextView name = findViewById(R.id.friendDetailName);
        TextView status = findViewById(R.id.friendDetailStatus);

        // Nhận dữ liệu Friend từ Intent
        Friend friend = getIntent().getParcelableExtra("friend_data");
        if (friend != null) {
            name.setText(friend.getName());
            status.setText(friend.getStatus());

            // Load avatar từ resource ID (nếu null → dùng avatar mặc định)
            int avatarRes = friend.getAvatarRes() != null ? friend.getAvatarRes() : R.drawable.avatar3;

            Glide.with(this)
                    .load(avatarRes)
                    .circleCrop()
                    .into(avatar);
        }
    }
}
