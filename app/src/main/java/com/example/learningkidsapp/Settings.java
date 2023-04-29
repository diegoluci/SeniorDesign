package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

        EditText fname, lname, phone, address;
        Button save, home;
        SharedPreferences sp;
        String fnameStr, lnameStr, phoneStr, addrStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fname = findViewById(R.id.FirstName);
        lname = findViewById(R.id.LastName);
        phone = findViewById(R.id.PhoneNumber);
        address = findViewById(R.id.Address);
        save = findViewById(R.id.Save);
        home = findViewById(R.id.Home);

       sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnameStr = fname.getText().toString();
                lnameStr = lname.getText().toString();
                phoneStr = phone.getText().toString();
                addrStr = address.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("FName", fnameStr);
                editor.putString("LName", lnameStr);
                editor.putString("Phone", phoneStr);
                editor.putString("Address", addrStr);

                editor.commit();
                Toast.makeText(Settings.this,"Information Saved", Toast.LENGTH_LONG).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentH = new Intent(Settings.this, MainActivity.class);
                startActivity(intentH);
            }
        });

    }
}