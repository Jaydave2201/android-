package com.example.carrental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyHolder> {

    Context context;
    ArrayList<SubCategoryList> arrayList;

    public SubCategoryAdapter(Context context, ArrayList<SubCategoryList> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public SubCategoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sub_category_design,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.MyHolder holder, int position) {

        holder.image.setImageResource(arrayList.get(position).getImage());
        holder.name.setText(arrayList.get(position).getName());
        holder.gear.setText(arrayList.get(position).getGearArray());
        holder.color.setText(arrayList.get(position).getColorArray());
        holder.seat.setText(arrayList.get(position).getSeatArray());
        holder.fual.setText(arrayList.get(position).getFuelArray());
        holder.ac.setText(arrayList.get(position).getAcArray());
        holder.price.setText(arrayList.get(position).getPriceArray());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name,gear,color,seat,fual,ac,price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.car_image);
            name = itemView.findViewById(R.id.car_name);
            gear = itemView.findViewById(R.id.car_gear);
            color = itemView.findViewById(R.id.car_color);
            seat = itemView.findViewById(R.id.car_seat);
            fual = itemView.findViewById(R.id.car_fuel);
            ac = itemView.findViewById(R.id.car_ac);
            price = itemView.findViewById(R.id.car_price);

        }
    }
}

