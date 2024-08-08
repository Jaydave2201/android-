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

public class SignupActivity extends AppCompatActivity {

    TextView submit;
    EditText name, password, email;
    SharedPreferences sp;
    RadioGroup radioGroup;
    String sGroup, sType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_mobile);
        password = findViewById(R.id.signup_password);
        submit = findViewById(R.id.signup_submit);

        radioGroup = findViewById(R.id.signup_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                sGroup = radioButton.getText().toString();
                if (sGroup.equalsIgnoreCase("User")) {
                    sType = "Accepted";
                } else {
                    sType = "Pending";
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(SignupActivity.this, "Please Select Login Type", Toast.LENGTH_SHORT).show();
                } else if (name.getText().toString().equals("")) {
                    name.setError("Name Required");
                } else if (email.getText().toString().equals("")) {
                    email.setError("Contact No. Required");
                } else if (email.getText().toString().length() < 10 || email.getText().toString().length() > 10) {
                    email.setError("Valid Contact No. Required");
                } else if (password.getText().toString().equals("")) {
                    password.setError("Password Required");
                } else {
                    Toast.makeText(SignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

    }

}
