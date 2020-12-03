package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;

public class MainActivity extends AppCompatActivity implements OnValueChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editNama;
    private ActualNumberPicker editTinggi;
    private ActualNumberPicker editBerat;
    private Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNama=findViewById(R.id.text_nama);
        editTinggi=(ActualNumberPicker) findViewById(R.id.height_picker);
        editTinggi.setListener(this);
        editBerat=findViewById(R.id.weight_picker);
        btnHitung=(Button)findViewById(R.id.button_kalkulasi);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editNama.getText().toString().trim();
                double tinggi= (float)(editTinggi.getValue());
                double berat= (float)(editBerat.getValue());
                String hasil;
                String ket;

                double bmi = berat/(tinggi*tinggi*0.0001);

                if (bmi<18.5){
                    hasil="Belum Ideal";
                    ket="Badan kamu berada dalam kondisi kurus";

                }else if(bmi>18.5 && bmi<24.9){
                    hasil="Ideal";
                    ket="Badan kamu berada dalam kondisi normal";
                }else if(bmi>25 && bmi<29.9){
                    hasil="Belum Ideal";
                    ket="Badan kamu berada dalam kondisi kelebihan berat badan";
                }else{
                    hasil="Belum Ideal";
                    ket="Badan kamu berada dalam kondisi obesitas";
                }

                Intent intent=new Intent(MainActivity.this,HasilKalkulasi.class);
                DecimalFormat df2 = new DecimalFormat("#.##");
                String bmiFOrmat = df2.format(bmi);
                boolean isIdeal = false;
                if (bmi>18.5 && bmi<24.9){
                    isIdeal = true;
                } else  {
                    isIdeal = false;
                }

                intent.putExtra("EXTRA_IDEAL", isIdeal);
                intent.putExtra("EXTRA_BMI", bmiFOrmat);
                intent.putExtra("EXTRA_HASIL",hasil);
                intent.putExtra("EXTRA_KET",ket);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onValueChanged(int oldValue, int newValue) {

    }
}