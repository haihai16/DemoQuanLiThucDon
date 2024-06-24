package com.example.quanlithucdon;

import androidx.annotation.NonNull;

public class MonAnModel {
    private int maMon;
    private String tenMon ;
    private int giaMon ;

    public MonAnModel(int maMon, String tenMon, int giaMon) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.giaMon = giaMon;
    }

    public MonAnModel(String tenMon, int giaMon) {
        this.tenMon = tenMon;
        this.giaMon = giaMon;
    }

    @NonNull
    @Override
    public String toString() {
        return tenMon +"  "+giaMon+"k";
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getGiaMon() {
        return giaMon;
    }

    public void setGiaMon(int giaMon) {
        this.giaMon = giaMon;
    }
}
