package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class forgotpass extends AppCompatActivity {

    private EditText emailEditText, newPasswordEditText, reenterPasswordEditText;
    private ImageView showNewPassword, hideNewPassword, showReenterPassword, hideReenterPassword;
    private Button changePasswordButton;
    private SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        // Initialize the SQLiteDatabase
        sqldb = openOrCreateDatabase("DIPLOMA2.db", MODE_PRIVATE, null);

        // Initialize Views
        emailEditText = findViewById(R.id.email);
        newPasswordEditText = findViewById(R.id.pass);
        reenterPasswordEditText = findViewById(R.id.pass1);
        showNewPassword = findViewById(R.id.show);
        hideNewPassword = findViewById(R.id.hide);
        showReenterPassword = findViewById(R.id.show1);
        hideReenterPassword = findViewById(R.id.hide1);
        changePasswordButton = findViewById(R.id.log);

        String tableQuery = "CREATE TABLE IF NOT EXISTS USERS (NAME VARCHAR(10), EMAIL VARCHAR(20), CONTACT BIGINT(10), PASSWORD VARCHAR(10), GENDER VARCHAR(10))";
        sqldb.execSQL(tableQuery);

        // Set up password visibility toggles
        showNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewPassword.setVisibility(View.GONE);
                hideNewPassword.setVisibility(View.VISIBLE);
                newPasswordEditText.setTransformationMethod(null);
            }
        });

        hideNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewPassword.setVisibility(View.VISIBLE);
                hideNewPassword.setVisibility(View.GONE);
                newPasswordEditText.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        showReenterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReenterPassword.setVisibility(View.GONE);
                hideReenterPassword.setVisibility(View.VISIBLE);
                reenterPasswordEditText.setTransformationMethod(null);
            }
        });

        hideReenterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReenterPassword.setVisibility(View.VISIBLE);
                hideReenterPassword.setVisibility(View.GONE);
                reenterPasswordEditText.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        // Set up change password button
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword(v);
            }
        });
    }

    private void changePassword(View view) {
        String email = emailEditText.getText().toString().trim();
        String newPassword = newPasswordEditText.getText().toString().trim();
        String reenterPassword = reenterPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email ID required");
        } else if (TextUtils.isEmpty(newPassword)) {
            newPasswordEditText.setError("New password required");
        } else if (newPassword.length() < 6) {
            newPasswordEditText.setError("Password must contain at least 6 characters");
        } else if (!newPassword.equals(reenterPassword)) {
            reenterPasswordEditText.setError("Passwords do not match");
        } else {
            String selectQuery = "SELECT * FROM USERS WHERE EMAIL='" + email + "'";
            Cursor cursor = sqldb.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {

                Intent intent = new Intent(forgotpass.this, MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Password Changed Successfully", Snackbar.LENGTH_LONG).show();
            } else {
                cursor.close();
                Toast.makeText(forgotpass.this, "Email ID doesn't match any user", Toast.LENGTH_LONG).show();
            }
        }
    }
}
