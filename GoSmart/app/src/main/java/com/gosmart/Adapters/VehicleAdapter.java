package com.gosmart.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gosmart.Activity.AddVehicleActivity;
import com.gosmart.R;
import com.gosmart.SetGet.VehicleList;
import com.gosmart.Utils.ConstantSp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

    Context context;
    ArrayList<VehicleList> vehicleLists;
    SharedPreferences sp;
    String sId, sNoPerson, sHistoryId;
    int iPosition, iFlag;
    Dialog dialog;

    public VehicleAdapter(FragmentActivity mainActivity, ArrayList<VehicleList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv, edit;
        TextView name, model, color, year, start;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            //iv = (ImageView) view.findViewById(R.id.custom_category_quiz_iv);
            name = (TextView) view.findViewById(R.id.custom_vehicle_name);
            model = (TextView) view.findViewById(R.id.custom_vehicle_model);
            color = (TextView) view.findViewById(R.id.custom_vehicle_color);
            year = (TextView) view.findViewById(R.id.custom_vehicle_year);
            iv = (ImageView) view.findViewById(R.id.custom_vehicle_image);
            edit = (ImageView) view.findViewById(R.id.custom_vehicle_edit);
            start = view.findViewById(R.id.custom_vehicle_start);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_vehicle, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        if (vehicleLists.get(position).getStartFlag() == 0) {
            holder.start.setText("Start Journey");
        } else {
            holder.start.setText("Stop Journey");
        }
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.model.setText(Html.fromHtml(vehicleLists.get(position).getModel()));
        holder.color.setText(Html.fromHtml(vehicleLists.get(position).getColor()));
        holder.year.setText(Html.fromHtml(vehicleLists.get(position).getYear()));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.ADD_VEHICLE_ADD_UPDATE, "Edit").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_TYPE, vehicleLists.get(position).getType()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_ID, vehicleLists.get(position).getId()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_REGISTRATION_NO, vehicleLists.get(position).getName()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL, vehicleLists.get(position).getModel()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL_SP, vehicleLists.get(position).getModelSP()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_COLOUR, vehicleLists.get(position).getColor()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_YEAR, vehicleLists.get(position).getYear()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_VIN, vehicleLists.get(position).getVin()).commit();
                context.startActivity(new Intent(context, AddVehicleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPosition = position;
                sId = vehicleLists.get(position).getId();
                sHistoryId = vehicleLists.get(position).getHistoryId();
                if (vehicleLists.get(position).getStartFlag() == 0) {
                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_start_journey);
                    EditText noPerson = dialog.findViewById(R.id.custom_start_journey_no);
                    TextView submit = dialog.findViewById(R.id.custom_start_journey_submit);
                    dialog.show();
                    dialog.setCancelable(false);

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (noPerson.getText().toString().trim().equalsIgnoreCase("")) {
                                noPerson.setError("No. Of Person Required");
                            } else {
                                sNoPerson = noPerson.getText().toString();
                                iFlag = 1;
                                Toast.makeText(context, "Start Successfully", Toast.LENGTH_SHORT).show();
                                VehicleList list = new VehicleList();
                                list.setId(vehicleLists.get(iPosition).getId());
                                list.setName(vehicleLists.get(iPosition).getName());
                                list.setType(vehicleLists.get(iPosition).getType());
                                list.setModel(vehicleLists.get(iPosition).getModel());
                                list.setModelSP(vehicleLists.get(iPosition).getModelSP());
                                list.setColor(vehicleLists.get(iPosition).getColor());
                                list.setYear(vehicleLists.get(iPosition).getYear());
                                list.setVin(vehicleLists.get(iPosition).getVin());
                                list.setHistoryId("1");
                                list.setStartFlag(iFlag);
                                //list.setImage(image[i]);
                                vehicleLists.set(iPosition, list);
                                notifyDataSetChanged();
                            }
                        }
                    });
                } else {
                    iFlag = 0;
                    Toast.makeText(context, "Stop Successfully", Toast.LENGTH_SHORT).show();
                    VehicleList list = new VehicleList();
                    list.setId(vehicleLists.get(iPosition).getId());
                    list.setName(vehicleLists.get(iPosition).getName());
                    list.setType(vehicleLists.get(iPosition).getType());
                    list.setModel(vehicleLists.get(iPosition).getModel());
                    list.setModelSP(vehicleLists.get(iPosition).getModelSP());
                    list.setColor(vehicleLists.get(iPosition).getColor());
                    list.setYear(vehicleLists.get(iPosition).getYear());
                    list.setVin(vehicleLists.get(iPosition).getVin());
                    list.setHistoryId("");
                    list.setStartFlag(iFlag);
                    //list.setImage(image[i]);
                    vehicleLists.set(iPosition, list);
                    notifyDataSetChanged();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }

}
