package com.example.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private final String currentUserId = "12345";
    private View header;

    public String getCurrentUserId() {
        return currentUserId;
    }

    // launcher để nhận kết quả khi tạo bài viết mới
    private final ActivityResultLauncher<Intent> createPostLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            Post newPost = result.getData().getParcelableExtra("new_post_object");
                            if (newPost == null) return;

                            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                            if (currentFragment instanceof HomeFragment) {
                                ((HomeFragment) currentFragment).addPostOnTop(newPost);
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        header = findViewById(R.id.header);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        TextView statusInput = findViewById(R.id.status_input);
        ImageView photoIcon = findViewById(R.id.photo_icon);
        ImageView ivHeaderAvatar = header.findViewById(R.id.ivHeaderAvatar);
        ImageView messengerIcon = header.findViewById(R.id.messenger_icon);

        // Load HomeFragment mặc định
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
            header.setVisibility(View.VISIBLE);
        }

        // BottomNavigationView
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                replaceFragment(new HomeFragment());
                header.setVisibility(View.VISIBLE);
            } else if (id == R.id.nav_friends) {
                replaceFragment(new FriendsFragment());
                header.setVisibility(View.GONE);
            } else if (id == R.id.nav_watch) {
                replaceFragment(new VideoFragment());
                header.setVisibility(View.GONE);
            } else if (id == R.id.nav_notifications) {
                replaceFragment(new NotificationsFragment());
                header.setVisibility(View.GONE);
            } else if (id == R.id.nav_marketplace) {
                replaceFragment(new MenuFragment());
                header.setVisibility(View.GONE);
            }
            return true;
        });

        // Tạo bài viết mới
        View.OnClickListener openCreatePost = v -> {
            Intent intent = new Intent(this, CreatePostActivity.class);
            createPostLauncher.launch(intent);
        };

        statusInput.setOnClickListener(openCreatePost);
        photoIcon.setOnClickListener(openCreatePost);

        // Mở ProfileActivity
        ivHeaderAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("USER_ID", getCurrentUserId());
            startActivity(intent);
        });

        // Mở MessengerActivity
        messengerIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, MessengerActivity.class);
            startActivity(intent);
        });
    }

    // Thay fragment
    private void replaceFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
