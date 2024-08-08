package com.gosmart.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gosmart.R;
import com.gosmart.Utils.ConstantSp;

public class SplashActivity extends AppCompatActivity {

    TextView text;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        text = findViewById(R.id.splash_text);
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getString(ConstantSp.ID, "").equals("")) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    if (sp.getString(ConstantSp.USERTYPE, "").equals("Admin")) {
                        startActivity(new Intent(SplashActivity.this, AdminDashboardActivity.class));
                        finish();
                    } else if (sp.getString(ConstantSp.USERTYPE, "").equals("User")) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else if (sp.getString(ConstantSp.USERTYPE, "").equals("Driver")) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                    }
                }

            }
        }, 1000);

    }
}
