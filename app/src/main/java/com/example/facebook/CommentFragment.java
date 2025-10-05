package com.example.facebook;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {

    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private final List<Comment> commentList = new ArrayList<>();

    private static final String ARG_POST_ID = "post_id";

    public static CommentFragment newInstance(String postId) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POST_ID, postId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args == null) return;
        String postId = args.getString(ARG_POST_ID);
        if (postId == null) return;

        recyclerView = view.findViewById(R.id.commentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        commentAdapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(commentAdapter);

        EditText input = view.findViewById(R.id.commentInput);
        Button sendBtn = view.findViewById(R.id.sendCommentBtn);

        sendBtn.setOnClickListener(v -> {
            String content = input.getText().toString().trim();
            if (!content.isEmpty()) {
                Comment comment = new Comment(
                        String.valueOf(System.currentTimeMillis()),
                        postId,
                        "Bạn",     // hoặc lấy tên người dùng thực
                        "",
                        content,
                        "Vừa xong"
                );
                commentList.add(comment);
                commentAdapter.notifyItemInserted(commentList.size() - 1);
                recyclerView.scrollToPosition(commentList.size() - 1);
                input.setText("");
            }
        });

        input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND ||
                (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                sendBtn.performClick();
                return true;
            }
            return false;
        });

        // TODO: Nếu muốn, có thể load comment cũ từ server hoặc DB theo postId
    }
}
