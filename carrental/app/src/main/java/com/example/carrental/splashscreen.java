package com.example.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splashscreen extends AppCompatActivity {
    ImageView splash;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        sp = getSharedPreferences(Constant.PREF,MODE_PRIVATE);
        sp = getSharedPreferences(cardetails.DET,MODE_PRIVATE);
        splash=findViewById(R.id.splashimg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sp.getString(Constant.EMAIL,"").equals("")) {
                    Intent intent = new Intent(splashscreen.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(splashscreen.this, "Please Login", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent= new Intent(splashscreen.this, homepage.class);
                    startActivity(intent);
                }
            }

        },3000);

    }
}