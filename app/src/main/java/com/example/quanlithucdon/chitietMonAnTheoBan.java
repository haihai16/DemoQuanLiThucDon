package com.example.quanlithucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class chitietMonAnTheoBan extends AppCompatActivity {
    TextView txtBillBan;
    Button btnThanhToan , btnTrangChu;
    ListView lvMenu, lvDsMonAnTheoBan;
    ArrayList<MonAnModel> dsMon = new ArrayList<>();
    ArrayList<chiTietDonCuaHang> dsBill = new ArrayList<>();

    databaseCuaHang databaseCuaHang = new databaseCuaHang(this);
    databaseMonAn databaseMonAn = new databaseMonAn(this);
    databaseLichSuCH databaseLichSuCH = new databaseLichSuCH(this);
    ArrayAdapter adapterMenu, adapterDanhSachMon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_mon_an_theo_ban);
        Intent intent = getIntent();
        int maBan = intent.getIntExtra("maBan", 0);
        lvMenu = findViewById(R.id.lvMenu1);
        lvDsMonAnTheoBan = findViewById(R.id.lvDanhSachMonAnBan);
        btnTrangChu  = findViewById(R.id.btnTrangChu);
        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(chitietMonAnTheoBan.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        btnThanhToan = findViewById(R.id.btnThanhToanBan);
        Cursor cursor = databaseMonAn.getdataMonAn();
        while (cursor.moveToNext()) {
            int maMon = cursor.getInt(0);
            String tenMon = cursor.getString(1);

            int giaMon = cursor.getInt(2);
            dsMon.add(new MonAnModel(maMon, tenMon, giaMon));


        }
        adapterMenu = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsMon);
        lvMenu.setAdapter(adapterMenu);

        Cursor cursor1 = databaseCuaHang.getDataMonAnTheoBan(maBan);
        while (cursor1.moveToNext()) {
            int maMon = cursor1.getInt(0);
            String tenMon = cursor1.getString(1);

            int giaMon = cursor1.getInt(2);
            int soLuongMon = cursor1.getInt(3);

            dsBill.add(new chiTietDonCuaHang(maMon, tenMon, giaMon, soLuongMon));


        }
        adapterDanhSachMon = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsBill);
        lvDsMonAnTheoBan.setAdapter(adapterDanhSachMon);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ma;
                Cursor cursor = databaseMonAn.getdataMonAn();
                int n = position + 1;
                while (cursor.moveToNext() && n > 0) {

                    n = n - 1;
                    ma = cursor.getInt(0);

                    if (n == 0) {


                        ChonMon(ma, maBan);
                    }

                }
            }
        });
        lvDsMonAnTheoBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ma;
                Cursor cursor = databaseCuaHang.getDataMonAnTheoBan(maBan);
                int n = position + 1;
                while (cursor.moveToNext() && n > 0) {

                    n = n - 1;
                    ma = cursor.getInt(0);

                    if (n == 0) {


                        xoaMon(ma, maBan);
                    }

                }
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThanhToan(maBan);
            }
        });
    }

    private void ChonMon(int ma, int maBanChon) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogchonmon);
        dialog.show();
        TextView txtTenMon, txtGia;
        EditText edtSoLuongMonChon;
        Button btnChonMon;
        txtTenMon = dialog.findViewById(R.id.txtTenMon);
        txtGia = dialog.findViewById(R.id.txtGiaMon);
        edtSoLuongMonChon = dialog.findViewById(R.id.edtSoLuongMonChon);
        btnChonMon = dialog.findViewById(R.id.btnChonMon);
        Cursor cursor = databaseMonAn.getdataMonAn();
        while (cursor.moveToNext()) {
            int maDT = cursor.getInt(0);
            if (ma == maDT) {
                txtTenMon.setText(cursor.getString(1));
                txtGia.setText(String.valueOf(cursor.getInt(2)));

            }
            btnChonMon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soLuong = Integer.parseInt(edtSoLuongMonChon.getText().toString());
                    String tenMon = txtTenMon.getText().toString();
                    int giaMon = Integer.parseInt(txtGia.getText().toString());
                    chiTietDonCuaHang monDuocChon = new chiTietDonCuaHang(tenMon, giaMon, soLuong, maBanChon);
                    databaseCuaHang.themMon(monDuocChon);
                    dialog.dismiss();
                    Toast.makeText(chitietMonAnTheoBan.this, "Them mon an vao danh sach thanh cong", Toast.LENGTH_SHORT).show();
                    dsBill.clear();
                    Cursor cursor1 = databaseCuaHang.getDataMonAnTheoBan(maBanChon);
                    while (cursor1.moveToNext()) {
                        int maMon1 = cursor1.getInt(0);
                        String tenMon1 = cursor1.getString(1);

                        int giaMon1 = cursor1.getInt(2);
                        int soLuongMon1 = cursor1.getInt(3);

                        dsBill.add(new chiTietDonCuaHang(maMon1, tenMon1, giaMon1, soLuongMon1));


                    }
                    adapterDanhSachMon.notifyDataSetChanged();
                }
            });

        }
    }

    private void xoaMon(int maMon, int maBan) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.xoamonanbill);
        dialog.show();
        TextView txtTenMon1, txtGiaMon1;
        EditText edtSoLuong1;
        Button btnXoaMonBill;
        txtTenMon1 = dialog.findViewById(R.id.txtTenMon1);
        txtGiaMon1 = dialog.findViewById(R.id.txtGiaMon1);
        edtSoLuong1 = dialog.findViewById(R.id.edtSoLuongMonChon1);
        btnXoaMonBill = dialog.findViewById(R.id.btnXoaMonBill);
        Cursor cursor = databaseCuaHang.getDataMonAnTheoBan(maBan);
        while (cursor.moveToNext()) {
            int maDT = cursor.getInt(0);
            if (maMon == maDT) {
                txtTenMon1.setText(cursor.getString(1));
                txtGiaMon1.setText(String.valueOf(cursor.getInt(2)));
                edtSoLuong1.setText(String.valueOf(cursor.getInt(3)));

            }
        }
        btnXoaMonBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseCuaHang.xoaMonAn(maMon) > 0)
                    Toast.makeText(chitietMonAnTheoBan.this, "Xoa Mon Thanh Cong", Toast.LENGTH_SHORT).show();
                else Toast.makeText(chitietMonAnTheoBan.this, "Xoa mon that bai", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dsBill.clear();
                Cursor cursor1 = databaseCuaHang.getDataMonAnTheoBan(maBan);
                while (cursor1.moveToNext()) {
                    int maMon1 = cursor1.getInt(0);
                    String tenMon1 = cursor1.getString(1);

                    int giaMon1 = cursor1.getInt(2);
                    int soLuongMon1 = cursor1.getInt(3);

                    dsBill.add(new chiTietDonCuaHang(maMon1, tenMon1, giaMon1, soLuongMon1));


                }
                adapterDanhSachMon.notifyDataSetChanged();
            }
        });

    }
    private void dialogThanhToan(int maBan){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogthanhtoanbill);
        dialog.show();
        TextView txtTongBill ;
        Button btnXacNhanThanhToan;
        txtTongBill = dialog.findViewById(R.id.txtTongBill);
        btnXacNhanThanhToan = dialog.findViewById(R.id.btnXacNhanThanhToan);
        int tongTien = 0;
        Cursor cursor1 = databaseCuaHang.getDataMonAnTheoBan(maBan);
        while (cursor1.moveToNext()) {
            int maMon1 = cursor1.getInt(0);
            String tenMon1 = cursor1.getString(1);

            int giaMon1 = cursor1.getInt(2);
            int soLuongMon1 = cursor1.getInt(3);

            tongTien += soLuongMon1 * giaMon1 ;


        }
        txtTongBill.setText(String.valueOf(tongTien));
        btnXacNhanThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseCuaHang.xoaMonAnid(maBan)>0){
                    Toast.makeText(chitietMonAnTheoBan.this, "Thanh Toan Thanh Cong", Toast.LENGTH_SHORT).show();}
                    else Toast.makeText(chitietMonAnTheoBan.this, "Thanh Toan That Bai", Toast.LENGTH_SHORT).show();
                lichSuGDCH lsGD = new lichSuGDCH(Integer.parseInt(txtTongBill.getText().toString()),maBan);
                databaseLichSuCH.themGD(lsGD);
                dialog.dismiss();
                    dsBill.clear();
                    adapterDanhSachMon.notifyDataSetChanged();

                    Intent intent = new Intent(chitietMonAnTheoBan.this,quanliocuahang.class);
                    startActivity(intent);

            }
        });
    }
}