package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RecycleItemModel implements Parcelable {
    private String url;
    private String title;
    private String date;
    private String content;
    private int count;

    public RecycleItemModel() {
    }

    protected RecycleItemModel(Parcel in) {
        url = in.readString();
        title = in.readString();
        date = in.readString();
        content = in.readString();
        count = in.readInt();
    }

    public static final Creator<RecycleItemModel> CREATOR = new Creator<RecycleItemModel>() {
        @Override
        public RecycleItemModel createFromParcel(Parcel in) {
            return new RecycleItemModel(in);
        }

        @Override
        public RecycleItemModel[] newArray(int size) {
            return new RecycleItemModel[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(url);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(content);
        parcel.writeInt(count);
    }
}
