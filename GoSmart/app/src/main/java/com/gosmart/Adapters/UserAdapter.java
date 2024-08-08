package com.gosmart.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gosmart.R;
import com.gosmart.SetGet.UserList;
import com.gosmart.Utils.ConstantSp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    Context context;
    ArrayList<UserList> arrayList;
    String sId,sStatus;
    int iPosition;

    public UserAdapter(FragmentActivity activity, ArrayList<UserList> arrayList) {
        this.context = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user, parent, false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name, contact, price, address, status, date, accept, reject;
        LinearLayout priceLayout, acceptRejectLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_user_name);
            contact = itemView.findViewById(R.id.custom_user_contact);
            price = itemView.findViewById(R.id.custom_user_price);
            address = itemView.findViewById(R.id.custom_user_address);
            status = itemView.findViewById(R.id.custom_user_status);
            date = itemView.findViewById(R.id.custom_user_date);
            accept = itemView.findViewById(R.id.custom_user_accept);
            reject = itemView.findViewById(R.id.custom_user_reject);
            priceLayout = itemView.findViewById(R.id.custom_user_price_layout);
            acceptRejectLayout = itemView.findViewById(R.id.custom_user_accept_reject_layout);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.contact.setText(arrayList.get(position).getEmail());
        holder.price.setText(arrayList.get(position).getPrice());
        if (arrayList.get(position).getAddress().equalsIgnoreCase("") && arrayList.get(position).getCity().equalsIgnoreCase("")) {
            holder.address.setText("-");
        } else if (arrayList.get(position).getAddress().equalsIgnoreCase("")) {
            holder.address.setText(arrayList.get(position).getCity());
        } else if (arrayList.get(position).getCity().equalsIgnoreCase("")) {
            holder.address.setText(arrayList.get(position).getAddress());
        } else {
            holder.address.setText(arrayList.get(position).getAddress() + "," + arrayList.get(position).getCity());
        }

        holder.status.setText(arrayList.get(position).getStatus());
        holder.date.setText(arrayList.get(position).getDate());

        if(arrayList.get(position).getUserType().equalsIgnoreCase("Driver") && arrayList.get(position).getStatus().equalsIgnoreCase("Pending")){
            holder.acceptRejectLayout.setVisibility(View.VISIBLE);
        }
        else{
            holder.acceptRejectLayout.setVisibility(View.GONE);
        }

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sId = arrayList.get(position).getId();
                    sStatus = "Accepted";
                    iPosition= position;
                    Toast.makeText(context, "Accepted Successfully", Toast.LENGTH_SHORT).show();
                    arrayList.remove(iPosition);
                    notifyDataSetChanged();
            }
        });

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sId = arrayList.get(position).getId();
                    sStatus = "Cancelled";
                    iPosition = position;
                    Toast.makeText(context, "Cancelled Successfully", Toast.LENGTH_SHORT).show();
                    arrayList.remove(iPosition);
                    notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
