package com.example.quanlithucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnOnline , btnCuaHang , btnDanhSachMon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCuaHang = findViewById(R.id.btnCuaHang);
        btnOnline = findViewById(R.id.btnOnl);
        btnDanhSachMon = findViewById(R.id.btnDanhSachMon);

        btnDanhSachMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Danhsachmonan.class);
                startActivity(intent);
            }
        });
        btnCuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , quanliocuahang.class);
                startActivity(intent);
            }
        });
        btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , ThongKeLichSuGD.class);
                startActivity(intent);
            }
        });
    }
}