package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    //initializing variables
    EditText fname, lname, phone, address;
    Button save, home, btnLogout;
    SharedPreferences sp;
    String fnameStr, lnameStr, phoneStr, addrStr;

    RadioGroup difficultyRadioGroup; // Declare the RadioGroup

    private static final String PREFS_NAME = "GameSettings";
    private static final String PREFS_DIFFICULTY_KEY = "SAVED_DIFFICULTY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Initializing XML elements
        fname = findViewById(R.id.FirstName);
        lname = findViewById(R.id.LastName);
        phone = findViewById(R.id.PhoneNumber);
        address = findViewById(R.id.Address);
        save = findViewById(R.id.Save);
        home = findViewById(R.id.Home);
        btnLogout = findViewById(R.id.btnlogout);
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);

        //Declaring Shared Preferences
        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        // Restore Difficulty Setting
        restoreDifficultySetting();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Assigns each buttons to a string of their own
                fnameStr = fname.getText().toString();
                lnameStr = lname.getText().toString();
                phoneStr = phone.getText().toString();
                addrStr = address.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("FName", fnameStr);
                editor.putString("LName", lnameStr);
                editor.putString("Phone", phoneStr);
                editor.putString("Address", addrStr);

                saveDifficultySetting(difficultyRadioGroup.getCheckedRadioButtonId());

                editor.commit();
                Toast.makeText(Settings.this, "Information Saved", Toast.LENGTH_LONG).show();
            }
        });

        //Once user saves their info they can go to Main Activity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentH = new Intent(Settings.this, MainActivity.class);
                startActivity(intentH);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear any session-related data here, if needed
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();  // Clear all stored data
                editor.commit(); // Commit the changes


                // Create a new intent to start the LoginActivity
                Intent intent = new Intent(Settings.this, LoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Difficulty Selection Listener
        difficultyRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                saveDifficultySetting(checkedId);
            }
        });
    }

        private void restoreDifficultySetting() {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            int savedDifficulty = sharedPreferences.getInt(PREFS_DIFFICULTY_KEY, -1);
            if (savedDifficulty != -1) {
                difficultyRadioGroup.check(savedDifficulty);
            }
        }

        private void saveDifficultySetting(int checkedId) {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(PREFS_DIFFICULTY_KEY, checkedId);
            editor.apply();
        }


    @Override
    protected void onPause() {
        super.onPause();

        // Check if the user is going back to the main activity
        if (!isChangingConfigurations() && !isFinishing()) {
            // The user is navigating back to the main activity
            // Start the BackgroundMusicService if it's not already running
            Intent serviceIntent = new Intent(this, BackgroundMusicService.class);
            startService(serviceIntent);
        } else {
            // The user is exiting the app or changing configurations
            // Stop the BackgroundMusicService
            Intent serviceIntent = new Intent(this, BackgroundMusicService.class);
            stopService(serviceIntent);
        }

    }

}
