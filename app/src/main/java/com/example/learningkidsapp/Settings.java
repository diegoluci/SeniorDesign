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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

        //initializing variables
        EditText fname, lname, phone, address;
        Button save, home, btnLogout;
        SharedPreferences sp;
        String fnameStr, lnameStr, phoneStr, addrStr;

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
        btnLogout = findViewById(R.id.btnLogout);

        //Declaring Shared Preferences
       sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

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

                editor.commit();
                Toast.makeText(Settings.this,"Information Saved", Toast.LENGTH_LONG).show();
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

                // Optional: If you're using Firebase Authentication, sign out from Firebase
                // FirebaseAuth.getInstance().signOut();

                // Create a new intent to start the LoginActivity
                Intent intent = new Intent(Settings.this, LoginActivity.class);

                // These flags will cause the LoginActivity to become the start of a new task on this history stack.
                // It should clear any old activities (including the game home screen) from the stack, ensuring that
                // the LoginActivity is the only thing on the stack. The user will not be able to go back to the game home screen
                // from the login screen by pressing the back button.
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Start the new activity
                startActivity(intent);

                // Call finish() on the current activity
                finish();  // This closes the current activity, preventing the user from navigating back to it
            }
        });


    }
}