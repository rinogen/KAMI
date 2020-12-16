package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BacaArtikel extends AppCompatActivity {
    private TextView txtJudul, txtDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_artikel);
        txtJudul= findViewById(R.id.judul_artikel);
        txtDeskripsi= findViewById(R.id.deskripsi_artikel);

        Intent intent= getIntent();
        TipsData tipsData = intent.getParcelableExtra("EXTRA_DATA");

        txtJudul.setText(tipsData.getJudulBerita());
        txtDeskripsi.setText(tipsData.getDescBerita());
    }
}