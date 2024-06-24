package com.example.quanlithucdon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseCuaHang extends SQLiteOpenHelper {
    private static String DATABASENAME = "Quanlicuahang";
    //Bảng môn học
    private static String TABLEDONCUAHANG = "tblBan";
    private static String MABAN = "maBan";
    private static String TRANGTHAIBAN= "trangThaiBan";
    private static String TABLEDANHSACHMONCHONCH = "tblDanhSachMonAnCH";
    private static String MAMONANCH = "maMonAnCH";
    private static String TENMONCH = "tenMonCH";
    private static String GIAMONCH = "giaMonCH";
    private static String SOLUONGMONCH= "soLuongMonCH";


    private static Context context;
    private String SQLQuery = "CREATE TABLE "+ TABLEDONCUAHANG +" ( "+MABAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "



            +TRANGTHAIBAN+" TEXT) ";
    private String SQLQuery1 = "CREATE TABLE "+ TABLEDANHSACHMONCHONCH +" ( "+MAMONANCH+" integer primary key AUTOINCREMENT, "
            +TENMONCH+" TEXT, "
            +GIAMONCH+" INTEGER, "
            +SOLUONGMONCH+" INTEGER, "

            +MABAN+" INTEGER , FOREIGN KEY ( "+ MABAN +" ) REFERENCES "+
            TABLEDONCUAHANG+"("+TABLEDONCUAHANG+"))";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
    }

    public databaseCuaHang(Context context){
        super(context,DATABASENAME,null,1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean themBan(CuaHangModel donhang){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();


        values.put(TRANGTHAIBAN,donhang.getTrangThai());




        db.insert(TABLEDONCUAHANG,null,values);
        //đóng lại db cho an toàn
        db.close();
        return true;
    }
    public Cursor getdataDonBan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLEDONCUAHANG,null);
        return res;
    }

    // het cac phuong thuc voi ban
    public Cursor getDataMonAnTheoBan(int id_subject){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLEDANHSACHMONCHONCH+" WHERE "+MABAN+" = "+id_subject,null);
        return res;
    }
    public int xoaMonAn(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLEDANHSACHMONCHONCH,MAMONANCH+" = "+i,null);
        return res;
    }
    public int xoaMonAnid(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLEDANHSACHMONCHONCH,MABAN+" = "+i,null);
        return res;
    }
    public boolean themMon(chiTietDonCuaHang phukien){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();

        values.put(TENMONCH,phukien.getTenMonCH());

        values.put(GIAMONCH,phukien.getGiaMonCH());
        values.put(SOLUONGMONCH,phukien.getSoLuong());
        values.put(MABAN,phukien.getMaBan());
        db.insert(TABLEDANHSACHMONCHONCH,null,values);
        //đóng lại db cho an toàn
        db.close();
        return true;
    }

}
