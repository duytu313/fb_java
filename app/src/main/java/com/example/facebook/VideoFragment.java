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

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private List<Video> videoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.videoRecyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Video demo với thumbnail
        videoList.add(new Video("dQw4w9WgXcQ", "Video 1", "https://img.youtube.com/vi/dQw4w9WgXcQ/0.jpg"));
        videoList.add(new Video("9bZkp7q19f0", "Video 2", "https://img.youtube.com/vi/9bZkp7q19f0/0.jpg"));
        videoList.add(new Video("3JZ_D3ELwOQ", "Video 3", "https://img.youtube.com/vi/3JZ_D3ELwOQ/0.jpg"));

        adapter = new VideoAdapter(videoList, getViewLifecycleOwner());
        adapter.attachRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        // Scroll listener để auto-play video giữa màn hình
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView rv, int newState) {
                super.onScrollStateChanged(rv, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstVisible = layoutManager.findFirstVisibleItemPosition();
                    int lastVisible = layoutManager.findLastVisibleItemPosition();
                    int targetPos = (firstVisible + lastVisible) / 2;
                    adapter.playVideoAt(targetPos);
                }
            }
        });

        // Play video đầu tiên
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.playVideoAt(0);
            }
        });
    }
}
