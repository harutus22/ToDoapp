package com.example.apple.todoapp.viewType;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {
    private String title, description, date, priority;

    public Info(){
    }

    public Info (String title, String description, String date, String prioity){
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = prioity;
    }

    protected Info(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
        priority = in.readString();
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(priority);
    }
}
