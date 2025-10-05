package com.example.facebook;

public class MenuItem {
    private int id;
    private int iconRes;
    private String title;

    // Constructor
    public MenuItem(int id, int iconRes, String title) {
        this.id = id;
        this.iconRes = iconRes;
        this.title = title;
    }

    // Getter
    public int getId() {
        return id;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getTitle() {
        return title;
    }

    // Nếu muốn, bạn có thể thêm setter
}
