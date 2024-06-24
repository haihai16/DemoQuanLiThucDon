package com.example.quanlithucdon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseMonAn extends SQLiteOpenHelper {
    private static String DATABASENAME = "DanhSachMonAn";
    //Bảng môn học
    private static String TABLEMONAN = "tblDanhSachMonAn";
    private static String MAMON = "maMon";
    private static String TENMON = "tenMon";
    private static String GIAMON = "giaMon";




    private static int VERSION = 1;



    private static Context context;
    private String SQLQuery = "CREATE TABLE "+ TABLEMONAN +" ( "+MAMON+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TENMON+" TEXT, "


            +GIAMON+" INTEGER) ";
    public databaseMonAn(Context context){super(context,DATABASENAME,null,1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }
    public boolean themMon(MonAnModel donhang){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();

        values.put(TENMON,donhang.getTenMon());
        values.put(GIAMON,donhang.getGiaMon());




        db.insert(TABLEMONAN,null,values);
        //đóng lại db cho an toàn
        db.close();
        return true;
    }
    public boolean suaMon( MonAnModel dienthoai, int maDT){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TENMON,dienthoai.getTenMon());
        values.put(GIAMON,dienthoai.getGiaMon());



        db.update(TABLEMONAN,values,MAMON+" = "+maDT,null);

        return true;
    }
    public int xoaMon(int maDT){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLEMONAN,MAMON+" = "+maDT,null);
        return res;
    }
    public Cursor getdataMonAn(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLEMONAN,null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
