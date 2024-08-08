package com.gosmart.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gosmart.R;
import com.gosmart.Utils.ConstantSp;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView submit;
    EditText password, confirmPassword, email;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        email = findViewById(R.id.forgot_mobile);
        password = findViewById(R.id.forgot_new_password);
        confirmPassword = findViewById(R.id.forgot_confirm_password);
        submit = findViewById(R.id.forgot_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")) {
                    email.setError("Email Id Required");
                } else if (password.getText().toString().equals("")) {
                    password.setError("New Password Required");
                } else if (confirmPassword.getText().toString().equals("")) {
                    confirmPassword.setError("Confirm Password Required");
                } else if (!confirmPassword.getText().toString().matches(password.getText().toString())) {
                    confirmPassword.setError("Confirm Password Does Not Match");
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

    }

}
