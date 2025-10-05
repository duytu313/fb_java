package com.example.facebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView storyRecyclerView;
    private RecyclerView postsRecyclerView;
    private PostAdapter postAdapter;
    private PostViewModel viewModel;
    private final String currentUserId = "YOUR_USER_ID";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // --- Story RecyclerView ---
        storyRecyclerView = view.findViewById(R.id.storyRecyclerView);
        storyRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        storyRecyclerView.setAdapter(new StoryAdapter(getSampleStories()));

        // --- Post RecyclerView ---
        postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // --- Lấy ViewModel dùng chung với Activity ---
        viewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        postAdapter = new PostAdapter(new ArrayList<>(), currentUserId, post -> {
            String postId = post.getId() != null ? post.getId() : "";
            CommentFragment fragment = CommentFragment.newInstance(postId);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();

            // Ẩn header khi vào comment
            HomeActivity activity = (HomeActivity) getActivity();
            if (activity != null) {
                View header = activity.findViewById(R.id.header);
                if (header != null) header.setVisibility(View.GONE);
            }
        });
        postsRecyclerView.setAdapter(postAdapter);

        // --- Observe LiveData từ ViewModel ---
        viewModel.getPosts().observe(getViewLifecycleOwner(), postList -> {
            postAdapter.updatePosts(postList);
        });

        // --- Tải bài viết từ server khi mở fragment ---
        viewModel.loadPostsFromServer();

        return view;
    }

    // --- Thêm bài viết mới lên đầu feed ---
    public void addPostOnTop(@NonNull Post newPost) {
        viewModel.addPost(newPost);          // cập nhật ViewModel & server
        postAdapter.addPostOnTop(newPost);   // cập nhật adapter
        postsRecyclerView.scrollToPosition(0);
    }

    // --- Sample Stories ---
    private List<Story> getSampleStories() {
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(R.drawable.avatar1, "Cường", true));
        stories.add(new Story(R.drawable.avatar2, "Bùi", true));
        stories.add(new Story(R.drawable.avatar3, "Chinh", false));
        stories.add(new Story(R.drawable.avatar4, "Quỳnh", true));
        stories.add(new Story(R.drawable.avatar5, "Hoàng", false));
        stories.add(new Story(R.drawable.avatar6, "Princess👑❤️", true));
        return stories;
    }
}
