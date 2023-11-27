package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class BootPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_page);
        //Creates scene to display BootPage for 2.7 seconds, after this, user is directed to the login activity
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intentB = new Intent(BootPage.this, LoginActivity.class);
            startActivity(intentB);
            finish();
        }, 2700);
    }
}