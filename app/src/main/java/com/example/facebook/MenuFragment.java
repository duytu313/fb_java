package com.example.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    // Chuyển currentUserId thành field của class
    private String currentUserId = "Unknown";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Lấy userId từ HomeActivity (nếu có)
        if (getActivity() instanceof HomeActivity) {
            currentUserId = ((HomeActivity) getActivity()).getCurrentUserId();
        }

        // Bấm vào icon bánh răng mở SettingsActivity
        ImageView ivSettings = view.findViewById(R.id.ivSettings);
        ivSettings.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SettingsActivity.class);
            startActivity(intent);
        });

        // Bấm vào item "Bạn bè" mở FriendsActivity
        LinearLayout llFriends = view.findViewById(R.id.llFriends);
        llFriends.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), FriendsActivity.class);
            startActivity(intent);
        });

        // Bấm vào avatar mở ProfileActivity
        ImageView ivProfileMenu = view.findViewById(R.id.ivProfileMenu);
        ivProfileMenu.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ProfileActivity.class);
            intent.putExtra("USER_ID", currentUserId); // OK vì là field
            startActivity(intent);
        });
    }
}
