package com.example.carrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;
import com.example.carrental.car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<car> carList;

    public CarAdapter(List<car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_list, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        car car = carList.get(position);
        holder.carName.setText(car.getName());
        holder.carPrice.setText("₹" + car.getPrice() + " Per Day");
        // Set the car image based on the car name or any other property
        switch (car.getName()) {

            case "Baleno":
                holder.carImage.setImageResource(R.drawable.baleno);
                break;
            case "Swift":
                holder.carImage.setImageResource(R.drawable.swift);
                break;
            case "Hondacity":
                holder.carImage.setImageResource(R.drawable.city);
                break;
            case "Nexa X16":
                holder.carImage.setImageResource(R.drawable.carxl6);
                break;
            case "Alto":
                holder.carImage.setImageResource(R.drawable.alto);
                break;
            case "Ertiga":
                holder.carImage.setImageResource(R.drawable.ertiga);
                break;
            case "Eeco":
                holder.carImage.setImageResource(R.drawable.eeco);
                break;
            case "Tiago":
                holder.carImage.setImageResource(R.drawable.tiago);
                break;
            case "Dzire":
                holder.carImage.setImageResource(R.drawable.dzire);
                break;
            case "Force van":
                holder.carImage.setImageResource(R.drawable.van);
                break;
            default:
                holder.carImage.setImageResource(R.drawable.car_placeholder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView carName, carPrice;
        public ImageView carImage;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.car_name);
            carPrice = itemView.findViewById(R.id.car_price);
            carImage = itemView.findViewById(R.id.car_image);
        }
    }
}
