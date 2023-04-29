package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button PhoneB;
    private Button NamesB;
    private Button AddressB;

    private ImageButton SettingsB;
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
            public void onClick(View view) { openSettings();
            }
        });

    }
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
}