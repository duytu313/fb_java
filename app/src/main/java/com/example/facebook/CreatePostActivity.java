package com.example.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {

    private final String currentUserId = "YOUR_USER_ID"; // TODO: Lấy từ login hoặc SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        EditText statusEditText = findViewById(R.id.status_edit_text);
        Button postButton = findViewById(R.id.post_button);

        postButton.setOnClickListener(v -> {
            String content = statusEditText.getText().toString().trim();
            if (!content.isEmpty()) {
                sendPostToServer(content);
            } else {
                Toast.makeText(CreatePostActivity.this, "Nhập nội dung bài viết", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPostToServer(String content) {
        RequestBody userIdBody = RequestBody.create(MediaType.parse("text/plain"), currentUserId);
        RequestBody contentBody = RequestBody.create(MediaType.parse("text/plain"), content);

        ApiClient.getApiService().createPost(userIdBody, contentBody, null)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        PostResponse body = response.body();
                        if (response.isSuccessful() && body != null && body.isSuccess() && body.getPost() != null) {
                            Toast.makeText(CreatePostActivity.this, "Đăng bài thành công", Toast.LENGTH_SHORT).show();

                            // Sửa ở đây: dùng PostResponse.PostData
                            PostResponse.PostData serverPost = body.getPost();

                            // Map sang Post của bạn
                            Post newPost = new Post(
                                    serverPost.get_id() != null ? serverPost.get_id() : String.valueOf(System.currentTimeMillis()),
                                    currentUserId,
                                    "Bạn", // tên hiển thị
                                    serverPost.getContent() != null ? serverPost.getContent() : "",
                                    null, // imageRes
                                    null, // videoRes
                                    serverPost.getCreatedAt() != null ? serverPost.getCreatedAt() : "Vừa xong",
                                    false
                            );

                            Intent resultIntent = getIntent();
                            resultIntent.putExtra("new_post_object", newPost);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        } else {
                            Toast.makeText(CreatePostActivity.this,
                                    body != null ? body.getMessage() : "Đăng bài thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(CreatePostActivity.this, "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
