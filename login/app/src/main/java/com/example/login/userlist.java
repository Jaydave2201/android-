package com.example.login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class userlist extends AppCompatActivity {


private List<user> userList = new ArrayList<>();
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userlist);
        userList.add(new user("jaydave",12,"jaydave2202@gmail.com",123456789));
    userList.add(new user("pushti",13,"pushti2202@gmail.com",123456789));
    userList.add(new user("pranjal",14,"pranjal2202@gmail.com",123456789));
    userList.add(new user("darshan",15,"darshan2202@gmail.com",123456789));

    }
}