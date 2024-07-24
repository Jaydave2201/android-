package com.example.carrental;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity {
    TextView welcomeText;
    SharedPreferences sp;
    ImageView xl,bal,sw,hc,al,et,ec,ti,dz,van;




    //Shared  Preference Name and key name create karvanu to use it for further same mainActivity jevu

    private static final String Shared_preferences_name = "mysp";
    private static final String Key_name = "username";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        welcomeText = findViewById(R.id.welcome);
        xl = findViewById(R.id.xl6);
        bal = findViewById(R.id.baleno);
        sw = findViewById(R.id.swift);
        hc = findViewById(R.id.hondacity);
        al = findViewById(R.id.alto);
        et = findViewById(R.id.ertiga);
        ec = findViewById(R.id.eeco);
        ti = findViewById(R.id.tiago);
        dz = findViewById(R.id.dzire);
        van = findViewById(R.id.van);
        sp = getSharedPreferences(Shared_preferences_name, MODE_PRIVATE);

        // Retrieve the stored name from SharedPreferences
        String userName = sp.getString(Key_name, "User");

        // Set the retrieved name to the TextView
        welcomeText.setText("Welcome " + userName);

        xl.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, nexaxl6.class);
            startActivity(intent);
        });
        bal.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, balenocar.class);
            startActivity(intent);
        });
        sw.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, swiftcar.class);
            startActivity(intent);
        });
        hc.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, hondacitycar.class);
            startActivity(intent);
        });
      al.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, altocar.class);
            startActivity(intent);
        });
        et.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, ertigacar.class);
            startActivity(intent);
        });
        ec.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, eecocar.class);
            startActivity(intent);
        });
        ti.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, tiagocar.class);
            startActivity(intent);
        });
        dz.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, dzirecar.class);
            startActivity(intent);
        });
        van.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, fordvan.class);
            startActivity(intent);
        });

    }
}
