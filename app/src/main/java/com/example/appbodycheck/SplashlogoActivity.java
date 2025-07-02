package com.example.appbodycheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashlogoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashlogo);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashlogoActivity.this, InputActivity.class));
            finish();
        }, 2000);
    }
}