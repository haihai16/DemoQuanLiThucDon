package com.example.quanlithucdon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseLichSuCH extends SQLiteOpenHelper {
    private static String DATABASENAME = "DanhSachGiaoDich2";
    //Bảng môn học
    private static String TABLEGIAODICH = "TBLGIAODICH";
    private static String MAGIAODICH = "MAGD";
    private static String GIATRIGD = "GIATRIGIAODICH";
    private static String MABANDUOCDAT = "MABANDUOCDAT";








    private static Context context;

    private String SQLQuery = "CREATE TABLE "+ TABLEGIAODICH +" " +
            "( "+MAGIAODICH+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +GIATRIGD+" INTEGER, "


            +MABANDUOCDAT+" INTEGER) ";
    public databaseLichSuCH(Context context){
        super(context,DATABASENAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean themGD(lichSuGDCH phukien){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();


        values.put(GIATRIGD,phukien.getGiaTriGD());
        values.put(MABANDUOCDAT,phukien.getMaBanDuocDat());
        db.insert(TABLEGIAODICH,null,values);
        //đóng lại db cho an toàn
        db.close();
        return true;
    }
    public Cursor getDataGD(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLEGIAODICH,null);
        return res;
    }
    public Cursor getDataBillTheoBan(int id_subject){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLEGIAODICH+" WHERE "+MABANDUOCDAT+" = "+id_subject,null);
        return res;
    }
}
