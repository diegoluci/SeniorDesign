package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;

import java.util.Locale;

public class AddrGame extends AppCompatActivity {

    //Initializing Variables
    TextView address;
    TextView question;
    EditText UserAnswer;
    Button check;
    String StreetNum;
    String StreetName;
    String StreetType;

    TextView timerTextView;
    CountDownTimer countDownTimer;
    long timeLeftInMillis = 60000; // 60 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr_game);

        //Initializing XML Elements
        address = findViewById(R.id.Address);
        question = findViewById(R.id.question);
        UserAnswer = findViewById(R.id.answer);
        check = findViewById(R.id.check);

        timerTextView = findViewById(R.id.timerTextView);

        // Start the timer
        startTimer();


        //Fetching Data From MyUserPrefs (Stored Data from Settings.java)
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String addr = sp.getString("Address","");
        //Variables for Parsing the Address
        int firstSpaceIndex = addr.indexOf(" ");
        int secondSpaceIndex = addr.indexOf(" ", addr.indexOf(" ") + 1);

        //if address is empty they must return to settings.java
        if (addr.isEmpty())
        {
            Intent intent = new Intent( this, Settings.class);
            startActivity(intent);
            finish();
        }
        else {
            if (addr.length() >=1)
            {
                //Initializing Variables
                StreetNum = addr.substring(0,firstSpaceIndex);
                StreetName = addr.substring(firstSpaceIndex + 1, secondSpaceIndex);
                StreetType = addr.substring(secondSpaceIndex +1);
                question.setText("Enter the Street Number You See:");
            }
        }

        //Set XML to show the user Street Number
        address.setText(StreetNum);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If their answer matches street number, XML is updated to show street name
                String answer = UserAnswer.getText().toString();
                if (answer.equals(StreetNum)) {
                    question.setText("Enter the Street name you See:");
                    address.setText(StreetName);
                    UserAnswer.setText("");
                }
                //If their answer matches street name, XML is updated to show street type
                if(answer.equals(StreetName)) {
                    question.setText("Enter the type of street you see:");
                    address.setText(StreetType);
                    UserAnswer.setText("");
                }
                //If their answer matches street type, game is done and sends to congrats screen
                if(answer.equals(StreetType)) {
                  Intent intent = new Intent(AddrGame.this, Congrats.class);
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
                // Redirect to a "Time's Up" screen or show a message
                Toast.makeText(AddrGame.this, "Time's up! Try again.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText("Timer: " + timeFormatted);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



}