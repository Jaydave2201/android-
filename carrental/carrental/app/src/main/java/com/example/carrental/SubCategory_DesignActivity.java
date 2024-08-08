package com.example.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubCategory_DesignActivity extends AppCompatActivity{

    ImageView imageView;
    TextView name,gear,seat,ac,color,fuel,price;
    SharedPreferences sp;
    Button bookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_sub_category_design);
         sp = getSharedPreferences(Constant.PREF,MODE_PRIVATE);
         imageView = findViewById(R.id.car_image);
         name = findViewById(R.id.car_name);
         gear = findViewById(R.id.car_gear);
         seat = findViewById(R.id.car_seat);
         ac = findViewById(R.id.car_ac);
         color = findViewById(R.id.car_color);
         fuel = findViewById(R.id.car_fuel);
         price = findViewById(R.id.car_price);

         bookNow = findViewById(R.id.book_now);

         bookNow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(SubCategory_DesignActivity.this, booking.class);
                 startActivity(intent);
             }
         });

         name.setText(sp.getString(Constant.CATEGORY_NAME,""));
         gear.setText(sp.getString(Constant.CATEGORY_GEAR,""));
         seat.setText(sp.getString(Constant.CATEGORY_SEAT,""));
         ac.setText(sp.getString(Constant.CATEGORY_AC,""));
         color.setText(sp.getString(Constant.CATEGORY_COLOR,""));
         fuel.setText(sp.getString(Constant.CATEGORY_FUEL,""));
         price.setText(sp.getString(Constant.CATEGORY_PRICE,""));

         imageView.setImageResource(Integer.parseInt(sp.getString(Constant.CATEGORY_IMAGE,"")));

    }
}