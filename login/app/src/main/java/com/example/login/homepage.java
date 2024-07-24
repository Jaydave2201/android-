package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {
    TextView welcomeText;
    SharedPreferences sp;
    Button logout;


    //Shared  Preference Name and key name create karvanu to use it for further same mainActivity jevu

    private static final String Shared_preferences_name = "mysp";
    private static final String Key_name = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                Intent intent= new Intent(homepage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        welcomeText = findViewById(R.id.welcome);
        sp = getSharedPreferences(Shared_preferences_name, MODE_PRIVATE);

        // Retrieve the stored name from SharedPreferences
        String userName = sp.getString(Key_name, "User");

        // Set the retrieved name to the TextView
        welcomeText.setText("Welcome " + userName);
    }
}
