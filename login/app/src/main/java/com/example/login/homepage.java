package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {
    TextView welcomeText;
    SharedPreferences sp;
    Button logout, delete;
    SQLiteDatabase sqldb;

    private static final String Shared_preferences_name = "mysp";
    private static final String Key_name = "username";
    private static final String DB_NAME = "DIPLOMA2.db";
    private static final String SELECT_USER_QUERY = "SELECT * FROM USERS WHERE EMAIL = ?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logout = findViewById(R.id.logout);
        delete = findViewById(R.id.delete);
        sqldb = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        // Initialize SharedPreferences
        sp = getSharedPreferences(Shared_preferences_name, MODE_PRIVATE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                Intent intent = new Intent(homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = sp.getString(Constant.EMAIL, "");
                if (!email.isEmpty()) {
                    // Delete user by email
                    sqldb.execSQL("DELETE FROM USERS WHERE EMAIL = ?", new Object[]{email});
                    sp.edit().clear().commit();
                    Intent intent = new Intent(homepage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        welcomeText = findViewById(R.id.welcome);

        // Retrieve the stored name from SharedPreferences
        String userName = sp.getString(Key_name, "User");

        // Set the retrieved name to the TextView
        welcomeText.setText("Welcome " + userName);
    }
}
