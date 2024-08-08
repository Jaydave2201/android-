package com.gosmart.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gosmart.R;
import com.gosmart.Utils.ConstantSp;

public class LoginActivity extends AppCompatActivity {

    TextView submit, signup, forgotPassword;
    EditText password, email;
    SharedPreferences sp;
    RadioGroup radioGroup;
    String sGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        email = findViewById(R.id.login_mobile);
        password = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit);
        signup = findViewById(R.id.login_signup);
        forgotPassword = findViewById(R.id.login_forgot);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        radioGroup = findViewById(R.id.login_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                sGroup = radioButton.getText().toString();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(LoginActivity.this, "Please Select Login Type", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().equals("")) {
                    email.setError("Contact No. Required");
                } else if (email.getText().toString().length() < 10 || email.getText().toString().length() > 10) {
                    email.setError("Valid Contact No. Required");
                } else if (password.getText().toString().equals("")) {
                    password.setError("Password Required");
                } else {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    if (sGroup.equalsIgnoreCase("Admin")) {
                        sp.edit().putString(ConstantSp.USERTYPE, sGroup).commit();
                        sp.edit().putString(ConstantSp.ID, "1").commit();
                        sp.edit().putString(ConstantSp.CONTACT, "9876543210").commit();
                        sp.edit().putString(ConstantSp.NAME, "Admin").commit();
                        sp.edit().putString(ConstantSp.PASSWORD, "admin@007").commit();
                        sp.edit().putString(ConstantSp.ADDRESS, "Ashramroad").commit();
                        sp.edit().putString(ConstantSp.AREA, "Incometax").commit();
                        sp.edit().putString(ConstantSp.CITY, "Ahmedabad").commit();
                        sp.edit().putString(ConstantSp.STATE, "Gujarat").commit();
                        sp.edit().putString(ConstantSp.STATUS, "Accepted").commit();
                        sp.edit().putString(ConstantSp.PRICE, "10").commit();
                        startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    } else if (sGroup.equalsIgnoreCase("Driver")) {
                        sp.edit().putString(ConstantSp.USERTYPE, sGroup).commit();
                        sp.edit().putString(ConstantSp.ID, "2").commit();
                        sp.edit().putString(ConstantSp.CONTACT, "9876543211").commit();
                        sp.edit().putString(ConstantSp.NAME, "Driver").commit();
                        sp.edit().putString(ConstantSp.PASSWORD, "driver@007").commit();
                        sp.edit().putString(ConstantSp.ADDRESS, "Ashramroad").commit();
                        sp.edit().putString(ConstantSp.AREA, "Incometax").commit();
                        sp.edit().putString(ConstantSp.CITY, "Ahmedabad").commit();
                        sp.edit().putString(ConstantSp.STATE, "Gujarat").commit();
                        sp.edit().putString(ConstantSp.STATUS, "Accepted").commit();
                        sp.edit().putString(ConstantSp.PRICE, "10").commit();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    } else if (sGroup.equalsIgnoreCase("User")) {
                        sp.edit().putString(ConstantSp.USERTYPE, sGroup).commit();
                        sp.edit().putString(ConstantSp.ID, "3").commit();
                        sp.edit().putString(ConstantSp.CONTACT, "9876543212").commit();
                        sp.edit().putString(ConstantSp.NAME, "User").commit();
                        sp.edit().putString(ConstantSp.PASSWORD, "user@007").commit();
                        sp.edit().putString(ConstantSp.ADDRESS, "Ashramroad").commit();
                        sp.edit().putString(ConstantSp.AREA, "Incometax").commit();
                        sp.edit().putString(ConstantSp.CITY, "Ahmedabad").commit();
                        sp.edit().putString(ConstantSp.STATE, "Gujarat").commit();
                        sp.edit().putString(ConstantSp.STATUS, "Accepted").commit();
                        sp.edit().putString(ConstantSp.PRICE, "10").commit();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    } else {

                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

