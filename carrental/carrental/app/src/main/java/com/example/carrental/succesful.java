package com.example.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class succesful extends AppCompatActivity {
    SharedPreferences sp;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_succesful);

        logout = findViewById(R.id.logout);

        // Initialize SharedPreferences
        sp = getSharedPreferences("YourPreferenceName", MODE_PRIVATE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                Intent intent = new Intent(succesful.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });
    }
}
