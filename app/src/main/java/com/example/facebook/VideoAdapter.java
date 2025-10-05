package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos;
    private LifecycleOwner lifecycleOwner;
    private int currentPlayingPosition = -1;
    private RecyclerView recyclerView;

    public VideoAdapter(List<Video> videos, LifecycleOwner lifecycleOwner) {
        this.videos = videos;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void attachRecyclerView(RecyclerView rv) {
        this.recyclerView = rv;
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView playerView;
        TextView title;
        ImageView thumbnail;
        YouTubePlayer youTubePlayer;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.itemYoutubePlayerView);
            title = itemView.findViewById(R.id.videoTitle);
            thumbnail = itemView.findViewById(R.id.videoThumbnail);
            youTubePlayer = null;
        }
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, final int position) {
        final Video video = videos.get(position);
        holder.title.setText(video.getTitle());

        // Load thumbnail
        Glide.with(holder.thumbnail.getContext())
                .load(video.getThumbnail())
                .into(holder.thumbnail);
        holder.thumbnail.setVisibility(View.VISIBLE);

        // Thêm observer lifecycle
        lifecycleOwner.getLifecycle().addObserver(holder.playerView);

        // Thêm listener
        holder.playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                holder.youTubePlayer = youTubePlayer;
                youTubePlayer.cueVideo(video.getId(), 0f);

                // Nếu đang play video này, play luôn
                if (position == currentPlayingPosition) {
                    holder.thumbnail.setVisibility(View.GONE);
                    youTubePlayer.play();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void playVideoAt(int position) {
        if (position == currentPlayingPosition) return;

        // Pause video cũ
        if (recyclerView != null) {
            RecyclerView.ViewHolder oldVH = recyclerView.findViewHolderForAdapterPosition(currentPlayingPosition);
            if (oldVH instanceof VideoViewHolder) {
                VideoViewHolder oldHolder = (VideoViewHolder) oldVH;
                if (oldHolder.youTubePlayer != null) {
                    oldHolder.youTubePlayer.pause();
                }
                oldHolder.thumbnail.setVisibility(View.VISIBLE);
            }
        }

        currentPlayingPosition = position;

        // Play video mới
        if (recyclerView != null) {
            RecyclerView.ViewHolder newVH = recyclerView.findViewHolderForAdapterPosition(position);
            if (newVH instanceof VideoViewHolder) {
                VideoViewHolder newHolder = (VideoViewHolder) newVH;
                newHolder.thumbnail.setVisibility(View.GONE);
                if (newHolder.youTubePlayer != null) {
                    newHolder.youTubePlayer.play();
                }
            }
        }
    }
}
