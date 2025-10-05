package com.example.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {

    private String id;
    private String name;
    private Integer avatarRes; // nullable Int trong Kotlin → Integer trong Java
    private String status;

    public Friend(String id, String name, Integer avatarRes, String status) {
        this.id = id;
        this.name = name;
        this.avatarRes = avatarRes;
        this.status = status;
    }

    // ===== Getters =====
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAvatarRes() {
        return avatarRes;
    }

    public String getStatus() {
        return status;
    }

    // ===== Parcelable Implementation =====
    protected Friend(Parcel in) {
        id = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            avatarRes = null;
        } else {
            avatarRes = in.readInt();
        }
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        if (avatarRes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(avatarRes);
        }
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}
