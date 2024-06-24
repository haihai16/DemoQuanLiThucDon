package com.example.quanlithucdon;

import androidx.annotation.NonNull;

public class lichSuGDCH {
    private int maGD ;
    private int giaTriGD ;
    private int maBanDuocDat;

    public lichSuGDCH(int maGD, int giaTriGD, int maBanDuocDat) {
        this.maGD = maGD;
        this.giaTriGD = giaTriGD;
        this.maBanDuocDat = maBanDuocDat;
    }

    public lichSuGDCH(int giaTriGD, int maBanDuocDat) {
        this.giaTriGD = giaTriGD;
        this.maBanDuocDat = maBanDuocDat;
    }

    @NonNull
    @Override
    public String toString() {
        return "GD:" + maGD +"+Ghi Chu <Ban " + (maBanDuocDat +1 ) +">" +"  Gia Tri:"+giaTriGD ;
    }

    public int getMaGD() {
        return maGD;
    }

    public void setMaGD(int maGD) {
        this.maGD = maGD;
    }

    public int getGiaTriGD() {
        return giaTriGD;
    }

    public void setGiaTriGD(int giaTriGD) {
        this.giaTriGD = giaTriGD;
    }

    public int getMaBanDuocDat() {
        return maBanDuocDat;
    }

    public void setMaBanDuocDat(int maBanDuocDat) {
        this.maBanDuocDat = maBanDuocDat;
    }
}
