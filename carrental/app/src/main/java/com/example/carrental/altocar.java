package com.example.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class altocar extends AppCompatActivity {
    TextView carname;
    SharedPreferences sp;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altocar);

        carname = findViewById(R.id.carname);
        book = findViewById(R.id.book);

        sp = getSharedPreferences(cardetails.NAME, MODE_PRIVATE);
        String storedCarName = sp.getString(cardetails.NAME, "");
        carname.setText(storedCarName);

        book.setOnClickListener(view -> {
            Intent intent = new Intent(altocar.this, booking.class);
            startActivity(intent);
        });
    }
}