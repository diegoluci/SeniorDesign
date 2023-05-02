package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        //Creates scene to display Congrats Page, then sends user to MainActivity after 1 second
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intentB = new Intent(Congrats.this, MainActivity.class);
            startActivity(intentB);
            finish();
        }, 1000);
    }
}

