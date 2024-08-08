package com.example.carrental;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class userList extends AppCompatActivity {
    private List<car> userlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_list);


        userlist.add(new car("Nexa X16", "Manual", "Red", 6, "Diesel", "Ac", "5,199", R.drawable.carxl6));
        userlist.add(new car("Baleno", "Manual", "White", 5, "Petrol", "Ac", "6,499", R.drawable.baleno));
        userlist.add(new car("Swift", "Automatic", "Metalic Grey", 5, "CNG", "Ac", "3,499", R.drawable.swift));
        userlist.add(new car("Hondacity", "Manual", "Black", 5, "Petrol", "Ac", "4,519",R.drawable.city));
        userlist.add(new car("Ertiga","Manual","Black",7,"petrol","Ac","6,499", R.drawable.ertiga));
        userlist.add(new car("Eeco","Manual","Black",7,"CNG","Ac","2,899",  R.drawable.eeco));
        userlist.add(new car("Tiago","Manual","Grey",5,"Petrol","Ac","4,899",  R.drawable.tiago));
        userlist.add(new car("Dzire","Manual","Blue",5,"petrol","Ac","4,999", R.drawable.dzire));
        userlist.add(new car("Force van","Automatic","Black",15,"Diesel","Ac","9,999",R.drawable.van));
        userlist.add(new car("Alto","Manual","Orange",5,"CNG","Ac","2,999", R.drawable.alto));

    }
}