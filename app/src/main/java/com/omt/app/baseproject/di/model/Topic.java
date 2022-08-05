package com.omt.app.baseproject.di.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Topic implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("totalTaskOpen")
    private Integer totalTaskOpen;
    @SerializedName("totalTaskDone")
    private Integer totalTaskDone;
    @SerializedName("totalTaskClose")
    private Integer totalTaskClose;


    protected Topic(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            totalTaskOpen = null;
        } else {
            totalTaskOpen = in.readInt();
        }
        if (in.readByte() == 0) {
            totalTaskDone = null;
        } else {
            totalTaskDone = in.readInt();
        }
        if (in.readByte() == 0) {
            totalTaskClose = null;
        } else {
            totalTaskClose = in.readInt();
        }
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTotalTaskOpen() {
        return totalTaskOpen;
    }

    public Integer getTotalTaskDone() {
        return totalTaskDone;
    }

    public Integer getTotalTaskClose() {
        return totalTaskClose;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
        if (totalTaskOpen == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalTaskOpen);
        }
        if (totalTaskDone == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalTaskDone);
        }
        if (totalTaskClose == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalTaskClose);
        }
    }
}
