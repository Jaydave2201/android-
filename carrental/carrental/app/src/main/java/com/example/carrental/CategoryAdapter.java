package com.example.carrental;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    Category_MainActivity context;
    int[] idArray;
    String[] nameArray;
    int[] imageArray;
    SharedPreferences sp;
    String[] gearArray;
    String[] seatArray;
    String[] acArray;
    String[] colorArray;
    String[] fuelType;
    String[] priceArray;

    public CategoryAdapter(Category_MainActivity context, int[] idArray, String[] nameArray, int[] imageArray, String[] gearArray, String[] seatArray, String[] acArray, String[] colorArray, String[] fuelType, String[] priceArray) {

        this.context=context;
        this.idArray=idArray;
        this.nameArray=nameArray;
        this.imageArray=imageArray;
        this.gearArray = gearArray;
        this.seatArray = seatArray;
        this.acArray = acArray;
        this.colorArray = colorArray;
        this.fuelType = fuelType;
        this.priceArray = priceArray;
        sp = context.getSharedPreferences(Constant.PREF,context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_design,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_image);
            name = itemView.findViewById(R.id.category_imagename);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(imageArray[position]);
        holder.name.setText(nameArray[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(Constant.CATEGORYID,String.valueOf(idArray[position])).commit();
                sp.edit().putString(Constant.CATEGORY_NAME,nameArray[position]).commit();
                sp.edit().putString(Constant.CATEGORY_IMAGE, String.valueOf(imageArray[position])).commit();
                sp.edit().putString(Constant.CATEGORY_GEAR,gearArray[position]).commit();
                sp.edit().putString(Constant.CATEGORY_SEAT,seatArray[position]).commit();
                sp.edit().putString(Constant.CATEGORY_AC,acArray[position]).commit();
                sp.edit().putString(Constant.CATEGORY_COLOR,colorArray[position]).commit();
                sp.edit().putString(Constant.CATEGORY_FUEL,fuelType[position]).commit();
                sp.edit().putString(Constant.CATEGORY_PRICE,priceArray[position]).commit();

                Intent intent = new Intent(context,SubCategory_DesignActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameArray.length;
    }

}
