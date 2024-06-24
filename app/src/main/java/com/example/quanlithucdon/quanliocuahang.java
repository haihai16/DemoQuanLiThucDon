package com.example.quanlithucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class quanliocuahang extends AppCompatActivity {
    Button btnThemBan , btnTrangChu1;
    ListView lvBan;
    ArrayAdapter adapterBan;
    ArrayList<CuaHangModel> dsBan = new ArrayList<>();
    databaseCuaHang databaseCuaHang = new databaseCuaHang(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanliocuahang);
        btnThemBan = findViewById(R.id.btnThemBan);
        btnTrangChu1 = findViewById(R.id.btnTrangChu1);
        btnTrangChu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quanliocuahang.this,MainActivity.class);
                startActivity(intent);
            }
        });
        lvBan = findViewById(R.id.lvDanhSachBan);
        Cursor cursor = databaseCuaHang.getdataDonBan();
        while (cursor.moveToNext()) {
            int maBan = cursor.getInt(0);
            String trangThaiBan = cursor.getString(1);


            dsBan.add(new CuaHangModel(maBan, trangThaiBan));


        }
        adapterBan = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsBan);
        lvBan.setAdapter(adapterBan);
        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trangThaiBan = "Trong";
                CuaHangModel BanMoi = new CuaHangModel(trangThaiBan);
                databaseCuaHang.themBan(BanMoi);
                dsBan.clear();
                Cursor cursor = databaseCuaHang.getdataDonBan();
                while (cursor.moveToNext()) {
                    int maBan = cursor.getInt(0);
                    String trangThaiBan1 = cursor.getString(1);


                    dsBan.add(new CuaHangModel(maBan, trangThaiBan1));


                }
                adapterBan.notifyDataSetChanged();

            }
        });
        lvBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ma;
                Cursor cursor = databaseCuaHang.getdataDonBan();
                int n = position + 1;
                while (cursor.moveToNext() && n > 0) {

                    n = n - 1;
                    ma = cursor.getInt(0);

                    if (n == 0) {


                        ThongtinBan(ma);
                    }

                }
            }
        });
    }
    private void ThongtinBan(int ma){
        Intent intent = new Intent(this , chitietMonAnTheoBan.class);
        intent.putExtra("maBan",ma);
        startActivity(intent);
    }
}