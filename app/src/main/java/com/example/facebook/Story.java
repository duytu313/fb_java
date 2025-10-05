package com.example.facebook;

public class Story {
    private int imageRes;
    private String name;
    private boolean isViewed;

    // Constructor
    public Story(int imageRes, String name, boolean isViewed) {
        this.imageRes = imageRes;
        this.name = name;
        this.isViewed = isViewed;
    }

    // Getter và Setter
    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }
}
