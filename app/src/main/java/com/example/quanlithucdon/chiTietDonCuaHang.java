package com.example.quanlithucdon;

import androidx.annotation.NonNull;

public class chiTietDonCuaHang {
    private int maMonAn;
    private String tenMonCH ;
    private int giaMonCH;
    private int soLuong;

    private int maBan;

    @NonNull
    @Override
    public String toString() {
        return tenMonCH + "   SL:"+soLuong;
    }

    public chiTietDonCuaHang(int maMonAn, String tenMonCH, int giaMonCH, int soLuong) {
        this.maMonAn = maMonAn;
        this.tenMonCH = tenMonCH;
        this.giaMonCH = giaMonCH;
        this.soLuong = soLuong;
        this.maBan = maBan;
    }

    public chiTietDonCuaHang(String tenMonCH, int giaMonCH, int soLuong, int maBan) {
        this.tenMonCH = tenMonCH;
        this.giaMonCH = giaMonCH;
        this.soLuong = soLuong;
        this.maBan = maBan;
    }

    public chiTietDonCuaHang(String tenMonCH, int giaMonCH, int soLuong) {
        this.tenMonCH = tenMonCH;
        this.giaMonCH = giaMonCH;
        this.soLuong = soLuong;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getTenMonCH() {
        return tenMonCH;
    }

    public void setTenMonCH(String tenMonCH) {
        this.tenMonCH = tenMonCH;
    }

    public int getGiaMonCH() {
        return giaMonCH;
    }

    public void setGiaMonCH(int giaMonCH) {
        this.giaMonCH = giaMonCH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }
}
