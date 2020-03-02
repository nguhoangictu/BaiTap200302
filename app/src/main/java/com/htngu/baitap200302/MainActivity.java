package com.htngu.baitap200302;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtMon;
    private ListView lvMon;
    private ArrayList<String> arrMon;
    private ArrayAdapter adapter;
    private int itemSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        lvMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("item", arrMon.get(i));
                startActivity(intent);
                return false;
            }
        });
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (itemSelected!=-1) lvMon.getChildAt(itemSelected).setBackgroundColor(Color.TRANSPARENT);
                itemSelected = i;
                lvMon.getChildAt(i).setBackgroundColor(Color.GRAY);

            }

        });
    }

    private void init() {
        edtMon = findViewById(R.id.edtMon);
        lvMon = findViewById(R.id.lvMonHoc);
        arrMon = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrMon);
        lvMon.setAdapter(adapter);
    }

    public void nhap(View view) {
        if (!edtMon.getText().toString().equals("")){
            arrMon.add(edtMon.getText().toString());
            adapter.notifyDataSetChanged();
        }else Toast.makeText(MainActivity.this, "Nhập dữ liệu!", Toast.LENGTH_SHORT).show();
    }
    public void update(View view) {
        if (itemSelected==-1){
            Toast.makeText(MainActivity.this, "Chọn môn học trước!", Toast.LENGTH_SHORT).show();
        }else if (!edtMon.getText().toString().equals("")){
            arrMon.set(itemSelected, edtMon.getText().toString());
            adapter.notifyDataSetChanged();
        } else Toast.makeText(MainActivity.this, "Nhập dữ liệu!", Toast.LENGTH_SHORT).show();
    }

}
