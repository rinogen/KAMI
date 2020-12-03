package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class HasilKalkulasi extends AppCompatActivity {
    private TextView txtBMI, txtHasil, txtKet;
    private String hasil,ket, bmi;
    private double defaultValue=0;
    private ImageView imgIlustrasiIdeal;
    boolean isIdeal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung);
        txtBMI=(TextView) findViewById(R.id.text_view_BMI);
        txtHasil=(TextView) findViewById(R.id.text_view_hasil);
        txtKet=(TextView) findViewById(R.id.text_view_ket);
        imgIlustrasiIdeal = findViewById(R.id.imgIlustrasiIdeal);

        Intent intent= getIntent();
        bmi= intent.getStringExtra("EXTRA_BMI");
        hasil= intent.getStringExtra("EXTRA_HASIL");
        ket = intent.getStringExtra("EXTRA_KET");
        isIdeal = intent.getBooleanExtra("EXTRA_IDEAL", false);

        if (isIdeal){
            Glide.with(this)
                    .load(R.drawable.ilustrasi_ideal)
                    .into(imgIlustrasiIdeal);
        }else {
            Glide.with(this)
                    .load(R.drawable.ilustrasi_belumideal)
                    .into(imgIlustrasiIdeal);
        }

        txtBMI.setText( bmi);
        txtHasil.setText(hasil);
        txtKet.setText(ket);

    }
}