package com.example.facebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private final List<NotificationItem> notificationList = Arrays.asList(
            new NotificationItem("Alice", "đã thích bài viết của bạn", R.drawable.avatar1, "2 giờ trước"),
            new NotificationItem("Bob", "đã bình luận: Tuyệt quá!", R.drawable.avatar2, "3 giờ trước"),
            new NotificationItem("Charlie", "đã theo dõi bạn", R.drawable.avatar3, "5 giờ trước")
    );

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvNotifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NotificationsAdapter(notificationList));

        return view;
    }
}
