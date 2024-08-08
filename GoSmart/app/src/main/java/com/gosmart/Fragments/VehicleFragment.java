package com.gosmart.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gosmart.Activity.AddVehicleActivity;
import com.gosmart.R;
import com.gosmart.Utils.ConstantSp;
import com.gosmart.Adapters.VehicleAdapter;
import com.gosmart.SetGet.VehicleList;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleFragment extends Fragment {

    FloatingActionButton add;

    RecyclerView recyclerView;
    ArrayList<VehicleList> arrayList;
    VehicleAdapter adapter;

    String[] id = {"1"};
    String[] name = {"GJ27BG1234"};
    String[] model = {"Yamaha Fz S Fi V 2.0"};
    String[] modelSP = {"FZ S FI (V 2.0) Dual Disc"};
    String[] color = {"Blue"};
    String[] year = {"2017"};
    String[] vin = {""};
    int[] image = {R.drawable.car};
    SharedPreferences sp;

    public VehicleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle, container, false);
        sp = getActivity().getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
        add = view.findViewById(R.id.vehicle_add);

        recyclerView = view.findViewById(R.id.vehicle_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        for(int i=0;i<2;i++){
            VehicleList list = new VehicleList();
            list.setId(String.valueOf(i));
            list.setName("GJ-27-BG-1234");
            list.setType("2W");
            list.setModel("Yamaha");
            list.setModelSP("FZ 2.0");
            list.setColor("Black");
            list.setYear("2017");
            list.setVin("VIN213");
            list.setHistoryId("1");
            list.setStartFlag(0);
            arrayList.add(list);
        }
        adapter = new VehicleAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.ADD_VEHICLE_ADD_UPDATE,"Add").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_TYPE,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_REGISTRATION_NO,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL_SP,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_COLOUR,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_YEAR,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_VIN,"").commit();
                startActivity(new Intent(getActivity(), AddVehicleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        return view;
    }

}
