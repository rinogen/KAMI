package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.example.kami.DataArtikel.getListDataArtikel;

public class TipsKesehatan extends AppCompatActivity {
    private Button btnmenu;
    private RecyclerView rvBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_kesehatan);
        btnmenu= findViewById(R.id.btn_bckmenu);

        rvBerita = findViewById(R.id.rv_tips);
        rvBerita.setLayoutManager(new LinearLayoutManager(this));
        rvBerita.setHasFixedSize(true);
        TipsAdapter tipsAdapter = new TipsAdapter(this, getListDataArtikel());
        rvBerita.setAdapter(tipsAdapter);
        tipsAdapter.notifyDataSetChanged();

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TipsKesehatan.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}