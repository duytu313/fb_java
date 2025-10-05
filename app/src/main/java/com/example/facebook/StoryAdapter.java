package com.example.facebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private List<Story> stories;

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        View online;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.story_avatar);
            name = itemView.findViewById(R.id.story_user_name);
            online = itemView.findViewById(R.id.story_onlineIndicator);
        }
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.avatar.setImageResource(story.getImageRes());
        holder.name.setText(story.getName());
        holder.online.setVisibility(story.isViewed() ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
