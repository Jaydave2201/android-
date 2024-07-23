package com.example.carrental;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carrental.forgotpass;
import com.example.carrental.homepage;
import com.example.carrental.signup;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText email, pass;
    TextView sign, forgot;
    ImageView hide, show;
    SQLiteDatabase sqldb;
    SharedPreferences sp;

    // Shared Preference Name and key name
    private static final String SHARED_PREFERENCES_NAME = "mysp";
    private static final String KEY_NAME = "username";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        button = findViewById(R.id.log);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        hide = findViewById(R.id.hide);
        show = findViewById(R.id.show);
        forgot = findViewById(R.id.forgot);
        sign = findViewById(R.id.sign);

        // Set up SharedPreferences
        sp = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);

        // Set up SQLite Database
        sqldb = openOrCreateDatabase("CARRENTAL.db", MODE_PRIVATE, null);
        String tableQuery = "CREATE TABLE if not exists USERS(NAME VARCHAR(10), EMAIL VARCHAR(20), CONTACT BIGINT(10), PASSWORD VARCHAR(10), GENDER VARCHAR(10))";
        sqldb.execSQL(tableQuery);

        // Toggle password visibility
        show.setOnClickListener(view -> {
            show.setVisibility(View.GONE);
            hide.setVisibility(View.VISIBLE);
            pass.setTransformationMethod(null);
            pass.setSelection(pass.length());
        });

        hide.setOnClickListener(view -> {
            show.setVisibility(View.VISIBLE);
            hide.setVisibility(View.GONE);
            pass.setTransformationMethod(new PasswordTransformationMethod());
            pass.setSelection(pass.length());
        });

        // Set click listeners
        button.setOnClickListener(this::onLoginButtonClick);
        forgot.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, forgotpass.class);
            startActivity(intent);
        });
        sign.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, signup.class);
            startActivity(intent);
        });
    }

    // Handle login button click
    private void onLoginButtonClick(View view) {
        SharedPreferences.Editor editor = sp.edit();
        String emailInput = email.getText().toString().trim();
        String passwordInput = pass.getText().toString().trim();
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.\\w+$";

        if (emailInput.isEmpty()) {
            email.setError("Email ID required");
        } else if (!Pattern.matches(emailPattern, emailInput)) {
            email.setError("Invalid email format");
        } else if (passwordInput.isEmpty()) {
            pass.setError("Password required");
        } else if (passwordInput.length() < 4) {
            pass.setError("Password must contain at least 6 characters");
        } else {
            String selectQuery = "SELECT NAME FROM USERS WHERE EMAIL=? AND PASSWORD=?";
            Cursor cursor = sqldb.rawQuery(selectQuery, new String[]{emailInput, passwordInput});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                String username = cursor.getString(0);

                editor.putString(KEY_NAME, username);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, homepage.class);
                intent.putExtra("userName", username);
                startActivity(intent);
                Snackbar.make(view, "Login Successfully", Snackbar.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Email ID and Password don't match", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }
}
