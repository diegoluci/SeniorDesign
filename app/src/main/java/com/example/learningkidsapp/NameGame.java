package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class NameGame extends AppCompatActivity {

    // Initializing Variables
    TextView usersName, Question, timerTextView;
    EditText Answer;
    Button checkBtn;

    CountDownTimer countDownTimer;
    long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_game);

        // Initialize XML Elements
        usersName = findViewById(R.id.UserName);
        Question = findViewById(R.id.question);
        Answer = findViewById(R.id.answer);
        checkBtn = findViewById(R.id.check);
        timerTextView = findViewById(R.id.timerTextView);

        // Fetch difficulty setting from SharedPreferences
        SharedPreferences gameSettings = getSharedPreferences("GameSettings", MODE_PRIVATE);
        int difficultySetting = gameSettings.getInt("SAVED_DIFFICULTY", -1);
        timeLeftInMillis = getTimeLimitBasedOnDifficulty(difficultySetting);

        // Start the timer
        startTimer();

        // Fetching Data From MyUserPrefs
        SharedPreferences sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String fname = sp.getString("FName", "");
        String lname = sp.getString("LName", "");

        if (fname.isEmpty() && lname.isEmpty()) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            finish();
        }

        usersName.setText(fname);

        checkBtn.setOnClickListener(view -> {
            String answer = Answer.getText().toString();
            if (answer.equals(fname)) {
                usersName.setText(lname);
                Answer.setText("");
            } else if (answer.equals(lname)) {
                Intent intent = new Intent(NameGame.this, Congrats.class);
                startActivity(intent);
            }
        });
    }

    private long getTimeLimitBasedOnDifficulty(int difficultySetting) {
        switch (difficultySetting) {
            case R.id.easyDifficulty:
                return 3 * 60 * 1000; // 3 minutes in milliseconds
            case R.id.mediumDifficulty:
                return 2 * 60 * 1000; // 2 minutes
            case R.id.hardDifficulty:
                return 1 * 60 * 1000; // 1 minute
            default:
                return 2 * 60 * 1000; // Default time
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            public void onFinish() {
                timeUp();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "Time remaining: %02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

    private void timeUp() {
        Toast.makeText(NameGame.this, "Time's up! Try again.", Toast.LENGTH_SHORT).show();
        finish();
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
        // Optional: Restart the timer here if required
    }
}


