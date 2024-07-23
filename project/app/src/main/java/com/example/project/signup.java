package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    Button log;
    EditText name, email, pass, contact;
    ImageView hide, show;
    RadioGroup gender;    String sGender;
    CheckBox chek;
    SQLiteDatabase sqldb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sqldb = openOrCreateDatabase("carental.db", MODE_PRIVATE, null);

        String tableQuery = "CREATE TABLE if not exists USERS (NAME VARCHAR(10), EMAIL VARCHAR(20), CONTACT BIGINT(10), PASSWORD VARCHAR(10), GENDER VARCHAR(10))";
        sqldb.execSQL(tableQuery);

        log = findViewById(R.id.log); // Initialize the log button
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        contact = findViewById(R.id.contact);
        hide = findViewById(R.id.hide);
        show = findViewById(R.id.show);
        chek = findViewById(R.id.main_chek);
        gender = findViewById(R.id.signup_gender);

        gender.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton rb = findViewById(i);
            sGender = rb.getText().toString();
        });

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

        log.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        String emailPattern = "^[a-zA-Z]+\\d+@\\w+\\.com$";
        String emailInput = email.getText().toString().trim();
        String passwordInput = pass.getText().toString().trim();
        String nameInput = name.getText().toString().trim();
        String contactInput = contact.getText().toString().trim();

        if (nameInput.isEmpty()) {
            name.setError("Name required");
        } else if (emailInput.isEmpty()) {
            email.setError("Email ID required");
        } else if (!Pattern.matches(emailPattern, emailInput)) {
            email.setError("Invalid email format");
        } else if (contactInput.isEmpty()) {
            contact.setError("Contact number required");
        } else if (!Pattern.matches("\\d{10}", contactInput)) {
            contact.setError("Invalid contact number");
        } else if (passwordInput.isEmpty()) {
            pass.setError("Password required");
        } else if (passwordInput.length() < 6) {
            pass.setError("Password must contain at least 6 characters");
        } else if (gender.getCheckedRadioButtonId() == -1) {
            new Commanmethod(signup.this, "Please Select Gender");
        } else if (!chek.isChecked()) {
           new Commanmethod(signup.this, "Please Accept Terms & Conditions");
        } else {
            // Check for existing user
            String selectQuery = "SELECT * FROM USERS WHERE EMAIL = ? OR CONTACT = ?";
            Cursor cursor = sqldb.rawQuery(selectQuery, new String[]{emailInput, contactInput});

            if (cursor.getCount() > 0) {
                Snackbar.make(view, "User already exists with this email or contact number", Snackbar.LENGTH_SHORT).show();
            } else {
                // Insert new user
                String insertQuery = "INSERT INTO USERS (NAME, EMAIL, CONTACT, PASSWORD, GENDER) VALUES (?, ?, ?, ?, ?)";
                sqldb.execSQL(insertQuery, new Object[]{nameInput, emailInput, contactInput, passwordInput, sGender});

                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Signup Successfully", Snackbar.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }
}
