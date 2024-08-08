package com.example.carrental;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class Category_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int[] idArray = {1,2,3,4,5,6,7,8,9,10};
    String[] nameArray = {"Nexa X16","Baleno","Swift","Honda City","Ertiga","Eeco","Alto","Tiago","Dzire","Force van"};
    int[] imageArray = {R.drawable.carxl6,R.drawable.baleno,R.drawable.swift,R.drawable.city,R.drawable.ertiga,R.drawable.eeco,R.drawable.alto,R.drawable.tiago,R.drawable.dzire,R.drawable.van};
    String[] gearArray = {"Auto","Manual","Manual","Manual","Manual","Manual","Manual","Manual","Manual","Manual"};
    String[] seatArray = {"5","7","4","5","7","4","5","7","4","7"};
    String[] acArray = {"AC","Non AC","AC","Non AC","AC","Non AC","AC","Non AC","AC","Non AC"};
    String[] colorArray = {"Black","White","Red","Black","White","Red","Black","White","Red","Blue"};
    String[] fuelType = {"Diesel","Petrol","CBG","Electric","Diesel","Petrol","CBG","Electric","Diesel","Petrol"};
    String[] priceArray = {"2000","2500","6000","2000","2500","6000","2000","2500","6000","2000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);

        recyclerView = findViewById(R.id.category_recyclerview);
        //recyclerView.setLayoutManager(new LinearLayoutManager(SubCategory_MainActivity.this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CategoryAdapter adapter = new CategoryAdapter(Category_MainActivity.this,idArray,nameArray,imageArray,gearArray,seatArray,acArray,colorArray,fuelType,priceArray);
        recyclerView.setAdapter(adapter);

    }
}