package com.example.quanlithucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ThongKeLichSuGD extends AppCompatActivity {
    Button btnTimKiem ;
    ListView lvLichSuGD ;
    EditText edtTimKiemBan ;
    ArrayAdapter adapterGD;
    ArrayList<lichSuGDCH> dsGD = new ArrayList<>();
    databaseLichSuCH databaseLichSuCH = new databaseLichSuCH(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_lich_su_gd);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        lvLichSuGD = findViewById(R.id.lvTKGD);
        edtTimKiemBan = findViewById(R.id.edtSTTBan);
        Cursor cursor = databaseLichSuCH.getDataGD();
        while (cursor.moveToNext()) {
            int maGD = cursor.getInt(0);
            int giaTriGD = cursor.getInt(1);

            int maBan = cursor.getInt(2);
            dsGD.add(new lichSuGDCH(maGD, giaTriGD, maBan));


        }
        adapterGD = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsGD);
        lvLichSuGD.setAdapter(adapterGD);

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maBanCanTim = Integer.parseInt(edtTimKiemBan.getText().toString()) - 1;
                dsGD.clear();
                Cursor cursor = databaseLichSuCH.getDataBillTheoBan(maBanCanTim);
                while (cursor.moveToNext()) {
                    int maGD = cursor.getInt(0);
                    int giaTriGD = cursor.getInt(1);

                    int maBan = cursor.getInt(2);
                    dsGD.add(new lichSuGDCH(maGD, giaTriGD, maBan));


                }
                adapterGD.notifyDataSetChanged();
            }
        });
    }
}