package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipsKesehatan extends AppCompatActivity {
    private Button btnmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_kesehatan);
        btnmenu= findViewById(R.id.btn_bckmenu);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TipsKesehatan.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}