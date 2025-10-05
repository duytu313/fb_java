package com.example.facebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReelsAdapter extends RecyclerView.Adapter<ReelsAdapter.VideoViewHolder> {

    private final List<Integer> videoList;

    public ReelsAdapter(List<Integer> videoList) {
        this.videoList = videoList;
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reel_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        int videoResId = videoList.get(position);
        Context context = holder.itemView.getContext();

        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + videoResId);
        holder.videoView.setVideoURI(uri);

        holder.videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            holder.videoView.start();
        });

        // Click mở VideoDetailActivity
        holder.videoView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoDetailActivity.class);
            intent.putExtra("video_res_id", videoResId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
