package com.example.carrental;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class SubCategory_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int[] idArray = {1,2,3,4,5,6,7,8,9,10};
    int[] categoryArray = {1,2,3,4,5,6,7,8,9,10};
    String[] nameArray = {"Nexa X16","Baleno","Swift","Honda City","Ertiga","Eeco","Alto","Tiago","Dzire","Force van"};
    int[] imageArray = {R.drawable.carxl6,R.drawable.baleno,R.drawable.swift,R.drawable.city,R.drawable.ertiga,R.drawable.eeco,R.drawable.alto,R.drawable.tiago,R.drawable.dzire,R.drawable.van};
    String[] gearArray = {"Auto"};
    String[] colorArray = {"Black"};
    String[] seatArray = {"5"};
    String[] fuelArray = {"Petrol"};
    String[] acArray = {"AC"};
    String[] priceArray = {"20000"};

    SharedPreferences sp;
    ArrayList<SubCategoryList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_main);

        // Initialize SharedPreferences
        sp = getSharedPreferences(Constant.CATEGORYID, MODE_PRIVATE);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.sub_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Initialize ArrayList
        arrayList = new ArrayList<>();
        for (int i = 0; i < idArray.length; i++) {
            if (Integer.parseInt(sp.getString(Constant.CATEGORYID, "")) == categoryArray[i]) {
                SubCategoryList list = new SubCategoryList();
                list.setId(idArray[i]);
                list.setCategoryid(categoryArray[i]);
                list.setName(nameArray[i]);
                list.setImage(imageArray[i]);
                list.setGearArray(gearArray[i]);
                list.setColorArray(colorArray[i]);
                list.setSeatArray(seatArray[i]);
                list.setFuelArray(fuelArray[i]);
                list.setAcArray(acArray[i]);
                list.setPriceArray(priceArray[i]);
                arrayList.add(list);
            }
        }

        // Set Adapter to RecyclerView
        SubCategoryAdapter adapter = new SubCategoryAdapter(SubCategory_MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
    }
}
