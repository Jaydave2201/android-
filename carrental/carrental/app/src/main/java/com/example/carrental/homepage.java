package com.example.carrental;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {
    TextView welcomeText;
    SharedPreferences sp;
    RecyclerView recyclerView;
    SQLiteDatabase sqldb;
    List<car> carList;
    CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        welcomeText = findViewById(R.id.welcome);
        recyclerView = findViewById(R.id.recycler_view);

        sp = getSharedPreferences(Constant.NAME, MODE_PRIVATE);
        sqldb = openOrCreateDatabase("CARDETAILS.db", MODE_PRIVATE, null);
        String tableQuery = "CREATE TABLE if not exists ADMIN(NAME VARCHAR(10), SEAT INT(2), FUEL VARCHAR(10), GEAR VARCHAR(10), PRICE VARCHAR(10))";
        sqldb.execSQL(tableQuery);

        welcomeText.setText("Welcome " + sp.getString(Constant.NAME, ""));

        carList = new ArrayList<>();
        carList.add(new car("Nexa X16", "Manual", "Red", 6, "Diesel", "Ac", "5,199", R.drawable.carxl6));
        carList.add(new car("Baleno", "Manual", "White", 5, "Petrol", "Ac", "6,499", R.drawable.baleno));
        carList.add(new car("Swift", "Automatic", "Metalic Grey", 5, "CNG", "Ac", "3,499", R.drawable.swift));
        carList.add(new car("Hondacity", "Manual", "Black", 5, "Petrol", "Ac", "4,519",R.drawable.city));
        carList.add(new car("Ertiga","Manual","Black",7,"petrol","Ac","6,499", R.drawable.ertiga));
        carList.add(new car("Eeco","Manual","Black",7,"CNG","Ac","2,899",  R.drawable.eeco));
        carList.add(new car("Alto","Manual","Orange",5,"CNG","Ac","2,999", R.drawable.alto));
        carList.add(new car("Tiago","Manual","Grey",5,"Petrol","Ac","4,899",  R.drawable.tiago));
        carList.add(new car("Dzire","Manual","Blue",5,"petrol","Ac","4,999", R.drawable.dzire));
        carList.add(new car("Force van","Automatic","Black",15,"Diesel","Ac","9,999",R.drawable.van));
       carAdapter = new CarAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carAdapter);
    }
}
