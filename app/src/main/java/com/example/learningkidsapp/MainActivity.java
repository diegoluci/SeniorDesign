package com.example.learningkidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    private Button PhoneB;
    private Button NamesB;
    private Button AddressB;
    private ImageButton SettingsB;
    private MediaPlayer mediaPlayer;  // Declare MediaPlayer as a member variable
    private boolean isMusicServiceRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhoneB = (Button) findViewById(R.id.Phone);
        PhoneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPhoneGame();
            }
        });

        NamesB = (Button) findViewById(R.id.Names);
        NamesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNameGame();
            }
        });

        AddressB = (Button) findViewById(R.id.Address);
        AddressB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddrGame();
            }
        });

        SettingsB = (ImageButton) findViewById(R.id.Settings);
        SettingsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        // Start the BackgroundMusicService
        boolean isMusicServiceRunning = false;
        Intent serviceIntent = new Intent(this, BackgroundMusicService.class);
        startService(serviceIntent);
        isMusicServiceRunning = true;


        // Call the playSong method to start playing the song

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stop the BackgroundMusicService when your activity is paused

        if (isFinishing()) {
            Intent serviceIntent = new Intent(this, BackgroundMusicService.class);
            stopService(serviceIntent);
            isMusicServiceRunning = false;
        }
        }



    // Intents for each button to open their respective activity
    public void openPhoneGame() {
        Intent intentP = new Intent(this, PhoneGame.class);
        startActivity(intentP);
    }

    public void openNameGame() {
        Intent intentN = new Intent(this, NameGame.class);
        startActivity(intentN);
    }

    public void openAddrGame() {
        Intent intentA = new Intent(this, AddrGame.class);
        startActivity(intentA);
    }

    public void openSettings() {
        Intent intentS = new Intent(this, Settings.class);
        startActivity(intentS);
    }

    // Method to play the song

}
