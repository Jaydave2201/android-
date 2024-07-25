package com.example.carrental;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class altocar extends AppCompatActivity {
    TextView carname;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altocar);
        sp = getSharedPreferences(cardetails.NAME, MODE_PRIVATE);
        carname = findViewById(R.id.carname);
        String storedCarName = sp.getString(cardetails.NAME, "");
        carname.setText(storedCarName);
    }
}
