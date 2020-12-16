package com.example.kami.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.kami.R;
import com.example.kami.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        int waktu_loading = 4000;
        new Handler().postDelayed(() -> {

            //setelah loading maka akan langsung berpindah ke home activity
            Intent home=new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(home);
            finish();

        }, waktu_loading);

    }
}