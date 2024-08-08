package com.gosmart.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gosmart.Activity.LoginActivity;
import com.gosmart.R;
import com.gosmart.Utils.ConstantSp;

public class ProfileFragment extends Fragment {

    SharedPreferences sp;
    EditText name, contact, password, address;
    Spinner spinner;
    Button submit, editProfile;

    String[] cityArray = {"Ahmedabad", "Vadodara", "Surat"};

    String sCity;
    Button logout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sp = getActivity().getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        logout = view.findViewById(R.id.profile_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sp.edit().remove(ConstantUrl.ID).commit();
                sp.edit().clear().commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        name = view.findViewById(R.id.profile_name);
        contact = view.findViewById(R.id.profile_contact);
        password = view.findViewById(R.id.profile_password);
        spinner = view.findViewById(R.id.profile_city);
        submit = view.findViewById(R.id.profile_submit);
        editProfile = view.findViewById(R.id.profile_edit_profile);

        address = view.findViewById(R.id.profile_address);

        name.setText(sp.getString(ConstantSp.NAME, ""));
        contact.setText(sp.getString(ConstantSp.CONTACT, ""));
        password.setText(sp.getString(ConstantSp.PASSWORD, ""));
        address.setText(sp.getString(ConstantSp.ADDRESS, ""));

        sCity = sp.getString(ConstantSp.CITY, "");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cityArray);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);

        for (int i = 0; i < cityArray.length; i++) {
            if (cityArray[i].equalsIgnoreCase(sCity)) {
                spinner.setSelection(i);
                break;
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sCity = cityArray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        name.setEnabled(false);
        contact.setEnabled(false);
        password.setEnabled(false);
        address.setEnabled(false);
        spinner.setEnabled(false);
        editProfile.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                contact.setEnabled(true);
                password.setEnabled(true);
                address.setEnabled(true);
                spinner.setEnabled(true);
                editProfile.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Name Required");
                } else if (contact.getText().toString().trim().equalsIgnoreCase("")) {
                    contact.setError("Contact No. Required");
                } else if (contact.getText().toString().trim().length() < 10) {
                    contact.setError("Valid Contact No. Required");
                } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError("Password Required");
                } else if (address.getText().toString().trim().equalsIgnoreCase("")) {
                    address.setError("Address Required");
                } else {
                    Toast.makeText(getActivity(), "Profile Update Successfully", Toast.LENGTH_SHORT).show();
                    name.setEnabled(false);
                    contact.setEnabled(false);
                    password.setEnabled(false);
                    address.setEnabled(false);
                    spinner.setEnabled(false);
                    editProfile.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.GONE);

                    sp.edit().putString(ConstantSp.NAME, name.getText().toString()).commit();
                    sp.edit().putString(ConstantSp.CONTACT, contact.getText().toString()).commit();
                    sp.edit().putString(ConstantSp.PASSWORD, password.getText().toString()).commit();
                    sp.edit().putString(ConstantSp.CITY, sCity).commit();
                    sp.edit().putString(ConstantSp.ADDRESS, address.getText().toString()).commit();
                }
            }
        });
        return view;
    }
}