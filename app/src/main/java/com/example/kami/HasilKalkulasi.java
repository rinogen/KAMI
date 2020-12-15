package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static android.view.Gravity.apply;

public class HasilKalkulasi extends AppCompatActivity {
    private TextView txtBMI, txtHasil, txtKet;
    private String hasil,ket, bmi;
    private double defaultValue=0;
    private ImageView imgIlustrasiIdeal;
    private Button btnAnalisis, btnTips;
    String isIdeal ;
    private static final String TAG = "HasilKalkulasi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung);
        txtBMI=(TextView) findViewById(R.id.text_view_BMI);
        txtHasil=(TextView) findViewById(R.id.text_view_hasil);
        txtKet=(TextView) findViewById(R.id.text_view_ket);
        imgIlustrasiIdeal = findViewById(R.id.imgIlustrasiIdeal);
        btnAnalisis=(Button) findViewById(R.id.btn_analisis);
        btnTips=(Button) findViewById(R.id.btn_tips);

        btnAnalisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HasilKalkulasi.this,AnalisisImt.class);
                startActivity(intent);
            }
        });

        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HasilKalkulasi.this,TipsKesehatan.class);
                startActivity(intent);
            }
        });

        Intent intent= getIntent();
        bmi= intent.getStringExtra("EXTRA_BMI");
        hasil= intent.getStringExtra("EXTRA_HASIL");
        ket = intent.getStringExtra("EXTRA_KET");
        isIdeal = intent.getStringExtra("EXTRA_IDEAL");

        Log.d(TAG, "onCreate: "+ isIdeal);
        if (isIdeal.equals("ideal")){
            btnAnalisis.setVisibility(View.INVISIBLE);
            Glide.with(this)
                    .load(R.drawable.ilustrasi_ideal)
                    .into(imgIlustrasiIdeal);
        }else if(isIdeal.equals("kurus")){
            Glide.with(this)
                    .load(R.drawable.ilustrasi_belumideal_kurus)
                    .into(imgIlustrasiIdeal);
        }else if(isIdeal.equals("gendut")){
            Glide.with(this)
                    .load(R.drawable.ilustrasi_belumideal_gendut)
                    .into(imgIlustrasiIdeal);
        }

        txtBMI.setText( bmi);
        txtHasil.setText(hasil);
        txtKet.setText(ket);
    }
}