package com.example.facebook;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        VideoView videoView = findViewById(R.id.videoView);

        int videoResId = getIntent().getIntExtra("videoResId", -1);
        if (videoResId != -1) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResId);
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(this));
            videoView.start();
        }
    }
}
