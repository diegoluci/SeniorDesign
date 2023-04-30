package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneGame extends AppCompatActivity {

    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bstar,bhash,bcall;
    ImageButton delbtn;
    TextView tv;
    String CurrentString = "";
    String Shortphone = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_game);

        TextView UserNum;
        UserNum = findViewById(R.id.UserNum);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String phone = sp.getString("Phone", "");
        Shortphone = phone.substring(0,3);
        UserNum.setText(Shortphone);

        b0 = findViewById(R.id.b0); b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2); b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4); b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6); b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8); b9 = findViewById(R.id.b9);
        bstar = findViewById(R.id.bstar); bhash = findViewById(R.id.bhash);
        bcall = findViewById(R.id.bcall); delbtn = findViewById(R.id.delbtn);

        tv = findViewById(R.id.txtphone);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "0";
                tv.setText(CurrentString);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "1";
                tv.setText(CurrentString);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "2";
                tv.setText(CurrentString);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "3";
                tv.setText(CurrentString);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "4";
                tv.setText(CurrentString);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "5";
                tv.setText(CurrentString);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "6";
                tv.setText(CurrentString);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "7";
                tv.setText(CurrentString);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "8";
                tv.setText(CurrentString);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "9";
                tv.setText(CurrentString);
            }
        });
        bstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "*";
                tv.setText(CurrentString);
            }
        });
        bhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentString += "#";
                tv.setText(CurrentString);
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CurrentString.length() > 0) {
                    CurrentString = CurrentString.substring(0, CurrentString.length() - 1);
                    tv.setText(CurrentString);
                }
            }
        });

        bcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     if (CurrentString.equals(Shortphone)) {
                         Intent intent = new Intent(PhoneGame.this, MainActivity.class);
                         startActivity(intent);
                     }
            }
        });


        }
    }
