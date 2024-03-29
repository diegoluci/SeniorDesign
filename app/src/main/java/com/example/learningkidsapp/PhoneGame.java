package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class PhoneGame extends AppCompatActivity {

    //Initializing Variables
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bstar,bhash,bcall;
    ImageButton delbtn;
    TextView tv;
    String CurrentString = "";
    String Area = "";
    String Middle = "";
    String Last = "";

    TextView timerTextView;
    CountDownTimer countDownTimer;
    long timeLeftInMillis = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_game);

        //Initializing XML Element for users number from settings.java
        TextView userNum;
        userNum = findViewById(R.id.UserNum);

        timerTextView = findViewById(R.id.timerTextView);

        // Initialize and start the CountDownTimer
        startTimer();

        //Fetching Data From MyUserPrefs (Stored Data from Settings.java)
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String phone = sp.getString("Phone", "");

        //If their information from settings page is empty, they are redirected to settings page to input it
        if (phone.isEmpty())
        {
            Intent intent = new Intent( this, Settings.class);
            startActivity(intent);
            finish();
        }
        //If their information is not empty, the string is updated to display the users first 3 digits
        else {
            if (phone.length() >=3)
            {
                Area = phone.substring(0,3);
                Middle = phone.substring(3,6);
                Last = phone.substring(6,10);
            }
        }

        //XML is updated to display users Area code
        userNum.setText(Area);

        //Initializing XML Elements
        b0 = findViewById(R.id.b0); b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2); b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4); b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6); b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8); b9 = findViewById(R.id.b9);
        bstar = findViewById(R.id.bstar); bhash = findViewById(R.id.bhash);
        bcall = findViewById(R.id.bcall); delbtn = findViewById(R.id.delbtn);

        tv = findViewById(R.id.txtphone);

        //Giving Buttons their functionality
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

        //On click of delete button, current string's length is subtracted by 1 (Deleting last input)
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

            //If their answer matches the area code, middle 3 digits are displayed
            if(CurrentString.equals(Area)) {
                userNum.setText(Middle);
            }
            //If their answer matches the area code + middle 3 digits, last 4 digits are displayed
            if(CurrentString.equals(Area + Middle)){
                userNum.setText(Last);
            }
            //if their answer matches the all 3 parses of phone number, game is finished
            if(CurrentString.equals(Area+ Middle + Last)) {
                Intent intent = new Intent(PhoneGame.this, Congrats.class);
                startActivity(intent);
            }

            }
        });


        }


    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            public void onFinish() {
                // Code to handle when the timer finishes
                Toast.makeText(PhoneGame.this, "Time's up! Try again.", Toast.LENGTH_SHORT).show();
                finish(); // Finish this activity and return to the previous one on the stack
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

        
    }
