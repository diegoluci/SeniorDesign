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

public class AddrGame extends AppCompatActivity {

    //Initializing Variables
    TextView address;
    TextView question;
    EditText UserAnswer;
    Button check;
    String StreetNum;
    String StreetName;
    String StreetType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr_game);

        //Initializing XML Elements
        address = findViewById(R.id.Address);
        question = findViewById(R.id.question);
        UserAnswer = findViewById(R.id.answer);
        check = findViewById(R.id.check);

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
}