package com.example.kami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;

import static com.example.kami.database.DatabaseContract.RiwayatColumns.BMI;
import static com.example.kami.database.DatabaseContract.RiwayatColumns.CONTENT_URI;
import static com.example.kami.database.DatabaseContract.RiwayatColumns.DATE;

public class MainActivity extends AppCompatActivity implements OnValueChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editNama;
    private TextView btnRiwayat;
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
        btnRiwayat= findViewById(R.id.riwayat);

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

                String isIdeal;
                if (bmi>18.5 && bmi<24.9){
                    isIdeal="ideal";
                }else if(bmi<18.5){
                    isIdeal="kurus";
                }else{
                    isIdeal="gendut";
                }

                ContentValues values = new ContentValues();
                values.put(BMI, bmiFOrmat);
                values.put(DATE, getCurrentDate());

                getContentResolver().insert(CONTENT_URI, values);
                Log.d(TAG, "onCreate: "+ isIdeal);
                intent.putExtra("EXTRA_IDEAL", isIdeal);
                intent.putExtra("EXTRA_BMI", bmiFOrmat);
                intent.putExtra("EXTRA_HASIL",hasil);
                intent.putExtra("EXTRA_KET",ket);
                startActivity(intent);
            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Riwayat.class);
                startActivity(intent);
            }
        });
    }

    // Mengambil tanggal dan jam
    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("in", "ID"));
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onValueChanged(int oldValue, int newValue) {

    }
}