package com.example.login;

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

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText email, pass;
    TextView sign, forgot;
    ImageView hide, show;
    SQLiteDatabase sqldb;
    SharedPreferences sp;

    //Shared  Preference Name and key name create karvanu to use it for further
    private static final String Shared_preferences_name = "mysp";
    private static final String Key_name = "username";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.email), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sign = findViewById(R.id.sign);
        button = findViewById(R.id.log);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        hide = findViewById(R.id.hide);
        show = findViewById(R.id.show);
        forgot = findViewById(R.id.forgot);
        sp = getSharedPreferences(Shared_preferences_name, MODE_PRIVATE);
        sqldb = openOrCreateDatabase("DIPLOMA2.db", MODE_PRIVATE, null);

        String tableQuery = "CREATE TABLE if not exists USERS(NAME VARCHAR(10), EMAIL VARCHAR(20), CONTACT BIGINT(10), PASSWORD VARCHAR(10), GENDER VARCHAR(10))";
        sqldb.execSQL(tableQuery);

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

        button.setOnClickListener(this::onClick);
        forgot.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, forgotpass.class);
            startActivity(intent);
        });
        sign.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, signup.class);
            startActivity(intent);
        });
    }

    private void onClick(View view) {
        // After clicking login, the data should be stored in SharedPreferences
        SharedPreferences.Editor editor = sp.edit();
        String emailInput = email.getText().toString().trim();
        String passwordInput = pass.getText().toString().trim();
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.\\w+$";

        if (emailInput.equals("")) {
            email.setError("Email ID required");
        } else if (!Pattern.matches(emailPattern, emailInput)) {
            email.setError("Invalid email format");
        } else if (passwordInput.equals("")) {
            pass.setError("Password required");
        } else if (passwordInput.length() < 6) {
            pass.setError("Password must contain at least 6 characters");
        } else {
            String selectQuery = "SELECT NAME, EMAIL FROM USERS WHERE EMAIL='" + emailInput + "' AND PASSWORD='" + passwordInput + "'";
            Cursor cursor = sqldb.rawQuery(selectQuery, null);
    //After clicking in loin the data should be shared Preference ena mate editor use karvanu
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                String username = cursor.getString(cursor.getColumnIndex("NAME"));

                editor.putString(Key_name, username);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, homepage.class);
                intent.putExtra("userName", username);
                startActivity(intent);
                Snackbar.make(view, "Login Successfully", Snackbar.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Email ID and Password Don't Match", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }
}
