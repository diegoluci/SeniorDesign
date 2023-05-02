package com.example.learningkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class NameGame extends AppCompatActivity {

    //Initializing Variables
        TextView usersName;
        TextView Question;
        EditText Answer;
        Button checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_game);

        //Initializing XML Elements
        usersName = findViewById(R.id.UserName);
        Question = findViewById(R.id.question);
        Answer = findViewById(R.id.answer);
        checkBtn = findViewById(R.id.check);

        //Fetching Data From MyUserPrefs (Stored Data from Settings.java)
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String fname = sp.getString("FName","");
        String lname = sp.getString("LName","");

        //If the strings are empty they must return to the settings.java
        if (fname.isEmpty() && lname.isEmpty())
        {
            Intent intent = new Intent( this, Settings.class);
            startActivity(intent);
            finish();
        }

        //Setting XML element to display first name
        usersName.setText(fname);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If Their answer matches first name, XML element displays last Name
                String answer = Answer.getText().toString();
                if (answer.equals(fname)) {
                    usersName.setText(lname);
                    Answer.setText("");
                    //If their answer matches last name, they finish the game and receive congrats page
                } else if (answer.equals(lname)) {
                    Intent intent = new Intent(NameGame.this, Congrats.class);
                    startActivity(intent);
                }
            }
        });

    }
}

