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





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sign = findViewById(R.id.sign);
        button = findViewById(R.id.log);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        hide = findViewById(R.id.hide);
        show = findViewById(R.id.show);
        forgot = findViewById(R.id.forgot);

        sqldb = openOrCreateDatabase("DIPLOMA2.db", MODE_PRIVATE, null);
        sp = getSharedPreferences(Constant.PREF,MODE_PRIVATE);

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

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()){
                    sp.edit().putString(Constant.NAME,cursor.getString(1)).commit();
                    sp.edit().putString(Constant.EMAIL,cursor.getString(2)).commit();
                    sp.edit().putString(Constant.CONTACT,cursor.getString(3)).commit();
                    sp.edit().putString(Constant.PASSWORD,cursor.getString(4)).commit();
                }
                Intent intent = new Intent(MainActivity.this, homepage.class);
                startActivity(intent);
                Snackbar.make(view, "Login Successfully", Snackbar.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Email ID and Password Don't Match", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }
}
