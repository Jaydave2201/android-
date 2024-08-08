package com.gosmart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gosmart.Adapters.UserAdapter;
import com.gosmart.R;
import com.gosmart.SetGet.UserList;

import java.util.ArrayList;

public class UserFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<UserList> arrayList;
    UserAdapter adapter;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        recyclerView = view.findViewById(R.id.user_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserList list = new UserList();
            list.setId(String.valueOf(i));
            list.setUserType("User");
            list.setName("User Name");
            list.setPassword("user@007");
            list.setEmail("9876543212");
            list.setAddress("Ashramroad");
            list.setArea("Incometax");
            list.setCity("Ahmedabad");
            list.setState("Gujarat");
            list.setPrice("10");
            list.setStatus("Approved");
            list.setDate("06-03-2023");
            arrayList.add(list);
        }
        adapter = new UserAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}