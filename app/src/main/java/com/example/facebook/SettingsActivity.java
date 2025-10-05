package com.example.facebook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        recyclerView = findViewById(R.id.settingsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo dữ liệu menu
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, R.drawable.ic_account, "Tài khoản"));
        menuItems.add(new MenuItem(2, R.drawable.ic_privacy, "Quyền riêng tư"));
        menuItems.add(new MenuItem(3, R.drawable.ic_language, "Ngôn ngữ"));
        menuItems.add(new MenuItem(4, R.drawable.ic_help, "Trợ giúp & hỗ trợ"));
        menuItems.add(new MenuItem(5, R.drawable.ic_logout, "Đăng xuất"));

        // Khởi tạo adapter và gán cho RecyclerView
        adapter = new MenuAdapter(menuItems, this);
        recyclerView.setAdapter(adapter);
    }
}
