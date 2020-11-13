package com.raneem.customlistview;

import android.view.View;
import android.widget.TextView;

public class Slide {

    String title;
    String fipath;
    private int img;
    String id;
    private boolean isBooked;


    public Slide(){}

    public Slide(String title, String fipath,int img) {
        this.title = title;
        this.fipath = fipath;
        this.img=img;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFipath() {
        return fipath;
    }

    public void setFipath(String fipath) {
        this.fipath = fipath;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
