package com.example.quanlithucdon;


import androidx.annotation.NonNull;

public class CuaHangModel {
    private int maBan;

    private String trangThai;

    public CuaHangModel(int maBan, String trangThai) {
        this.maBan = maBan;
        this.trangThai = trangThai;
    }

    public CuaHangModel(String trangThai) {
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ban " + (maBan + 1) ;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
