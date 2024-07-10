package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    Button log;
    EditText name, email, pass, contact;
    ImageView hide, show;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        log = findViewById(R.id.log); // Initialize the log button
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        contact = findViewById(R.id.contact);
        hide = findViewById(R.id.hide);
        show = findViewById(R.id.show);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                pass.setTransformationMethod(null);
                pass.setSelection(pass.length());
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);
                pass.setTransformationMethod(new PasswordTransformationMethod());
                pass.setSelection(pass.length());
            }
        });

        log.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        String emailPattern = "^[a-zA-Z]+\\d+@\\w+\\.com$";
        String emailInput = email.getText().toString().trim();
        String passwordInput = pass.getText().toString().trim();
        String nameInput = name.getText().toString().trim();
        String contactInput = contact.getText().toString().trim();

        if (nameInput.equals("")) {
            name.setError("Name required");
        } else if (emailInput.equals("")) {
            email.setError("Email ID required");
        } else if (!Pattern.matches(emailPattern, emailInput)) {
            email.setError("Invalid email format");
        } else if (contactInput.equals("")) {
            contact.setError("Contact number required");
        } else if (passwordInput.equals("")) {
            pass.setError("Password required");
        } else if (passwordInput.length() < 6) {
            pass.setError("Password must contain at least 6 characters");
        } else {
            Intent intent = new Intent(signup.this, MainActivity.class);
            startActivity(intent);
            Snackbar.make(view,"Signup Succesfully",Snackbar.LENGTH_SHORT).show();

        }
    }
}