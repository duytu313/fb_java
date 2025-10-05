package com.example.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendsAdapter friendsAdapter;
    private final List<Friend> friendsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = view.findViewById(R.id.friendsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Adapter + callback
        friendsAdapter = new FriendsAdapter(
                friendsList,
                friend -> Toast.makeText(requireContext(),
                        "Đã gửi lời mời kết bạn tới " + friend.getName(),
                        Toast.LENGTH_SHORT).show(),
                friend -> {
                    Intent intent = new Intent(requireContext(), FriendDetailActivity.class);
                    intent.putExtra("friend_data", friend);
                    startActivity(intent);
                }
        );

        recyclerView.setAdapter(friendsAdapter);

        // Dữ liệu mẫu
        friendsList.add(new Friend("1", "Nguyễn Văn A", R.drawable.avatar1, "Online"));
        friendsList.add(new Friend("2", "Trần Thị B", R.drawable.avatar2, "Offline"));
        friendsList.add(new Friend("3", "Lê Văn C", R.drawable.avatar3, "Online"));
        friendsList.add(new Friend("4", "Phạm Thị D", R.drawable.avatar4, "Offline"));
        friendsAdapter.notifyDataSetChanged();

        return view;
    }

    // Thêm bạn mới vào đầu danh sách
    public void addNewFriend(Friend friend) {
        friendsList.add(0, friend);
        friendsAdapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }
}
