package com.gosmart.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gosmart.Adapters.HistoryAdapter;
import com.gosmart.R;
import com.gosmart.SetGet.HistoryList;
import com.gosmart.Utils.ConstantSp;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HistoryList> arrayList;
    HistoryAdapter adapter;
    
    SharedPreferences sp;
    
    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        sp = getActivity().getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HistoryList list = new HistoryList();
            list.setId(String.valueOf(i));
            list.setVehicleId(String.valueOf(i));
            list.setVehicleUserId(String.valueOf(i));
            list.setName("GJ-27-BG-1234");
            list.setType("2W");
            list.setModel("Yamaha");
            list.setModelSP("FZ 2.0");
            list.setColor("Black");
            list.setYear("2017");
            list.setVin("VIN213");
            list.setVehiclecreated_date("01-03-2023");
            list.setUserId(String.valueOf(i));
            list.setOwnerName("Owner Name");
            list.setNoPerson("1");
            list.setPassengerId("PSG123");
            list.setCustomerName("Customer Name");
            list.setPassengerNoPerson("1");
            list.setContactNo("9876543211");
            list.seteStatus(sp.getString(ConstantSp.REQUEST_STATUS,""));
            list.setRequestStatus("");
            list.setCreated_date("05-03-2023");

            list.setOwnerPrice("10");
            list.setTotalDistance("300");
            list.setPrice("10");
            list.setTotalPrice("3000");
            list.setPaymentType("Online");
            list.setTransactionId("TRN1234");

            //list.setImage(image[i]);
            arrayList.add(list);
        }
        adapter = new HistoryAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}