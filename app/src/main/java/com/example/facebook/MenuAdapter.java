package com.example.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final List<MenuItem> items;
    private final Context context;

    public MenuAdapter(List<MenuItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.ivIcon.setImageResource(item.getIconRes());
        holder.tvTitle.setText(item.getTitle());

        holder.itemView.setOnClickListener(v -> {
            switch (item.getId()) {
                case 1:
                    // Mở AccountActivity
                    // Intent intent1 = new Intent(context, AccountActivity.class);
                    // context.startActivity(intent1);
                    break;
                case 2:
                    // Mở PrivacyActivity
                    break;
                case 3:
                    // Mở LanguageActivity
                    break;
                case 4:
                    // Mở HelpActivity
                    break;
                case 5:
                    // Đăng xuất → về LoginActivity
                    SharedPreferences prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    prefs.edit().clear().apply();

                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
