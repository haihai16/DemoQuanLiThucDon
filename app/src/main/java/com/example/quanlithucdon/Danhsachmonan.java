package com.example.quanlithucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Danhsachmonan extends AppCompatActivity {
    Button btnThemMon ;
    ListView lvMonAn;
    ArrayAdapter adapterMonAn;
    ArrayList<MonAnModel> dsMonAn = new ArrayList<>();
    databaseMonAn databaseMonAn = new databaseMonAn(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachmonan);
//        btnLamMoi = findViewById(R.id.btnLamMoi);
        btnThemMon = findViewById(R.id.btnThemMon);
        lvMonAn = findViewById(R.id.lvMonAn);

        Cursor cursor = databaseMonAn.getdataMonAn();
        while (cursor.moveToNext()) {
            int maMon = cursor.getInt(0);
            String tenMon = cursor.getString(1);

            int giaMon = cursor.getInt(2);
            dsMonAn.add(new MonAnModel(maMon, tenMon, giaMon));


        }
        adapterMonAn = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsMonAn);
        lvMonAn.setAdapter(adapterMonAn);
//        btnLamMoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dsMonAn.clear();
//                Cursor cursor = databaseMonAn.getdataMonAn();
//                while (cursor.moveToNext()) {
//                    int maMon = cursor.getInt(0);
//                    String tenMon = cursor.getString(1);
//
//                    int giaMon = cursor.getInt(2);
//                    dsMonAn.add(new MonAnModel(maMon, tenMon, giaMon));
//
//
//                }
//                adapterMonAn.notifyDataSetChanged();
//            }
//        });
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemMon();

            }
        });
        lvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ma;
                Cursor cursor = databaseMonAn.getdataMonAn();
                int n = position + 1;
                while (cursor.moveToNext() && n > 0) {

                    n = n - 1;
                    ma = cursor.getInt(0);

                    if (n == 0) {


                        SuaXoaDialog(ma);
                    }

                }
            }
        });
    }
    private void dialogThemMon(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogthemmon);
        dialog.show();
        Button btnThemMon2;
        EditText edtTenMonMoi , edtGiaMonMoi;
        edtGiaMonMoi = dialog.findViewById(R.id.edtGiaMonMoi);
        edtTenMonMoi = dialog.findViewById(R.id.edtTenMonAnMoi);
        btnThemMon2 = dialog.findViewById(R.id.btnThemMonMoi2);
        btnThemMon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMonMoi = edtTenMonMoi.getText().toString();
                int giaMonMoi = Integer.parseInt(edtGiaMonMoi.getText().toString());
                MonAnModel monanmoi = new MonAnModel(tenMonMoi,giaMonMoi);
                databaseMonAn.themMon(monanmoi);
                Toast.makeText(Danhsachmonan.this, "Them Mon An Moi thanh cong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dsMonAn.clear();
                Cursor cursor = databaseMonAn.getdataMonAn();
                while (cursor.moveToNext()) {
                    int maMon = cursor.getInt(0);
                    String tenMon = cursor.getString(1);

                    int giaMon = cursor.getInt(2);
                    dsMonAn.add(new MonAnModel(maMon, tenMon, giaMon));


                }
                adapterMonAn.notifyDataSetChanged();
            }
        });

    }
    private void SuaXoaDialog(int ma){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogsuaxoamonan);
        dialog.show();
        Button btnSuaMon , btnXoaMon ;
        EditText edtTenMon , edtGiaMon;
        edtGiaMon = dialog.findViewById(R.id.edtGiaMonMoi);
        edtTenMon = dialog.findViewById(R.id.edtTenMonAnMoi);
        btnSuaMon = dialog.findViewById(R.id.btnSuaMon);
        btnXoaMon = dialog.findViewById(R.id.btnXoaMon);
        Cursor cursor = databaseMonAn.getdataMonAn();
        while (cursor.moveToNext()) {
            int maDT = cursor.getInt(0);
            if(ma == maDT){
                edtTenMon.setText(cursor.getString(1));
                edtGiaMon.setText(String.valueOf(cursor.getInt(2)));

            }


        }
        btnSuaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMon = edtTenMon.getText().toString();
                int giaMon = Integer.parseInt(edtGiaMon.getText().toString());


                MonAnModel dienthoai = new MonAnModel(tenMon, giaMon);
                if(databaseMonAn.suaMon(dienthoai,ma)){
                    Toast.makeText(Danhsachmonan.this, "Sua thong tin Mon An thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(Danhsachmonan.this, "Sua that bai", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dsMonAn.clear();
                Cursor cursor1 = databaseMonAn.getdataMonAn();
                while (cursor1.moveToNext()) {
                    int maMon1 = cursor1.getInt(0);
                    String tenMon1 = cursor1.getString(1);

                    int giaMon1 = cursor1.getInt(2);
                    dsMonAn.add(new MonAnModel(maMon1, tenMon1, giaMon1));


                }
                adapterMonAn.notifyDataSetChanged();
            }
        });
        btnXoaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseMonAn.xoaMon(ma) > 0){
                    Toast.makeText(Danhsachmonan.this, "Xoa Mon An thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(Danhsachmonan.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dsMonAn.clear();
                Cursor cursor1 = databaseMonAn.getdataMonAn();
                while (cursor1.moveToNext()) {
                    int maMon = cursor1.getInt(0);
                    String tenMon = cursor1.getString(1);

                    int giaMon = cursor1.getInt(2);
                    dsMonAn.add(new MonAnModel(maMon, tenMon, giaMon));


                }
                adapterMonAn.notifyDataSetChanged();
            }
        });


    }
}