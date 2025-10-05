package com.example.facebook;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Lấy userId từ Intent, nếu null thì mặc định "Unknown"
        String userId = getIntent().getStringExtra("USER_ID");
        if (userId == null) userId = "Unknown";

        // Lấy reference các view từ layout
        TextView tvUsername = findViewById(R.id.tvUsername);
        ImageView ivAvatar = findViewById(R.id.ivAvatarProfile);

        // Hiển thị thông tin tạm thời
        tvUsername.setText("User ID: " + userId);

        // Load avatar tạm bằng Glide (URL ví dụ)
        String avatarUrl = "https://example.com/avatar/" + userId + ".png";
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.avatar1) // ảnh tạm
                .error(R.drawable.avatar1)       // ảnh khi lỗi
                .into(ivAvatar);
    }
}
