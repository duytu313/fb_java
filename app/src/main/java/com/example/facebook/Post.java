package com.example.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private String id;          // ID từ server
    private String userId;      // ID người dùng đăng
    private String userName;    // Tên người đăng
    private String content;     // Nội dung bài viết
    private Integer imageRes;   // Resource ID ảnh (có thể null)
    private String videoId;     // ID video YouTube (có thể null)
    private String createdAt;   // Thời gian tạo
    private boolean isLiked;    // Trạng thái like

    public Post() {}

    public Post(String id, String userId, String userName, String content,
                Integer imageRes, String videoId, String createdAt, boolean isLiked) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.imageRes = imageRes;
        this.videoId = videoId;
        this.createdAt = createdAt;
        this.isLiked = isLiked;
    }

    // --- Getter & Setter ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Integer getImageRes() { return imageRes; }
    public void setImageRes(Integer imageRes) { this.imageRes = imageRes; }

    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }

    // --- Parcelable ---
    protected Post(Parcel in) {
        id = in.readString();
        userId = in.readString();
        userName = in.readString();
        content = in.readString();
        if (in.readByte() == 0) {
            imageRes = null;
        } else {
            imageRes = in.readInt();
        }
        videoId = in.readString();
        createdAt = in.readString();
        isLiked = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(content);
        if (imageRes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageRes);
        }
        dest.writeString(videoId);
        dest.writeString(createdAt);
        dest.writeByte((byte) (isLiked ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
