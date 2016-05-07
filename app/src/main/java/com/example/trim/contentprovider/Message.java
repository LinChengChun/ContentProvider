package com.example.trim.contentprovider;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/4/24.
 */
public class Message {
    private String address;
    private String body;
    private String time;
    private Drawable drawable;

    public Message(String address, String body, String time, Drawable drawable) {
        this.address = address;
        this.body = body;
        this.time = time;
        this.drawable = drawable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
