package com.gosmart.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.gosmart.Adapters.HistoryAdapter;
import com.gosmart.Fragments.HistoryFragment;
import com.gosmart.Fragments.ProfileFragment;
import com.gosmart.R;
import com.gosmart.SetGet.HistoryList;
import com.gosmart.Utils.ConstantSp;
import com.gosmart.Fragments.VehicleFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout vehicle_layout, ownhistory_layout, history_layout, price_layout, profile_layout, logout_layout;
    ImageView home_menu, vehicle_iv, ownhistory_iv, history_iv, price_iv, profile_iv, logout_iv;
    TextView header_title, title_menu, vehicle_text, ownhistory_text, history_text, price_text, profile_text, logout_text;

    SharedPreferences sp;

    /*RecyclerView recyclerView;
    ArrayList<VehicleList> arrayList;
    VehicleAdapter adapter;

    String[] id = {"1","2","3","4","5","6","7","8"};
    String[] name = {"Accident","Battery Jump Start","Clutch/Break problem","Fuel Problem","Lost/Locked Key","Towing","Tyre Damage","Other"};
    int[] image = {R.drawable.car_crash,R.drawable.battery,R.drawable.break_tyre,R.drawable.oil,R.drawable.key,R.drawable.towing,R.drawable.tyre,R.drawable.other};*/

    private static final int STORAGE_PERMISSION_CODE = 123;

    RecyclerView recyclerView;
    ArrayList<HistoryList> arrayList;
    HistoryAdapter adapter;

    Dialog dialog;
    String sPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestStoragePermission();
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        sp.edit().putString(ConstantSp.COUNT, "No").commit();
        home_menu = findViewById(R.id.content_main_menu);
        title_menu = findViewById(R.id.content_main_title);

        title_menu.setText("Home");

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        header_title = header.findViewById(R.id.header_title);

        header_title.setText(sp.getString(ConstantSp.NAME, ""));

        vehicle_layout = header.findViewById(R.id.header_vehicle_layout);
        vehicle_iv = header.findViewById(R.id.header_vehicle_iv);
        vehicle_text = header.findViewById(R.id.header_vehicle_text);

        ownhistory_layout = header.findViewById(R.id.header_own_history_layout);
        ownhistory_iv = header.findViewById(R.id.header_own_history_iv);
        ownhistory_text = header.findViewById(R.id.header_own_history_text);

        history_layout = header.findViewById(R.id.header_history_layout);
        history_iv = header.findViewById(R.id.header_history_iv);
        history_text = header.findViewById(R.id.header_history_text);

        price_layout = header.findViewById(R.id.header_price_layout);
        price_iv = header.findViewById(R.id.header_price_iv);
        price_text = header.findViewById(R.id.header_price_text);

        profile_layout = header.findViewById(R.id.header_profile_layout);
        profile_iv = header.findViewById(R.id.header_profile_iv);
        profile_text = header.findViewById(R.id.header_profile_text);

        if (sp.getString(ConstantSp.USERTYPE, "").equalsIgnoreCase("User")) {
            ownhistory_layout.setVisibility(View.VISIBLE);
            vehicle_layout.setVisibility(View.GONE);
            history_layout.setVisibility(View.GONE);
            price_layout.setVisibility(View.GONE);
        } else if (sp.getString(ConstantSp.USERTYPE, "").equalsIgnoreCase("Driver")) {
            ownhistory_layout.setVisibility(View.GONE);
            history_layout.setVisibility(View.VISIBLE);
            vehicle_layout.setVisibility(View.VISIBLE);
            price_layout.setVisibility(View.VISIBLE);
        } else {
            ownhistory_layout.setVisibility(View.VISIBLE);
            history_layout.setVisibility(View.VISIBLE);
            vehicle_layout.setVisibility(View.VISIBLE);
            price_layout.setVisibility(View.GONE);
        }

        logout_layout = header.findViewById(R.id.header_logout_layout);
        logout_iv = header.findViewById(R.id.header_logout_iv);
        logout_text = header.findViewById(R.id.header_logout_text);

        vehicle_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("Vehicles");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new VehicleFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        vehicle_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("Vehicles");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new VehicleFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        vehicle_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("Vehicles");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new VehicleFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        ownhistory_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.REQUEST_STATUS, "All").commit();
                sp.edit().putString(ConstantSp.REQUEST_USER_TYPE, "User").commit();
                title_menu.setText("Own History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        ownhistory_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.REQUEST_STATUS, "All").commit();
                sp.edit().putString(ConstantSp.REQUEST_USER_TYPE, "User").commit();
                title_menu.setText("Own History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        ownhistory_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.REQUEST_STATUS, "All").commit();
                sp.edit().putString(ConstantSp.REQUEST_USER_TYPE, "User").commit();
                title_menu.setText("Own History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        history_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.REQUEST_STATUS, "All").commit();
                sp.edit().putString(ConstantSp.REQUEST_USER_TYPE, "Foreman").commit();
                title_menu.setText("Travelling History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        history_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.REQUEST_STATUS, "All").commit();
                sp.edit().putString(ConstantSp.REQUEST_USER_TYPE, "Foreman").commit();
                title_menu.setText("Travelling History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        history_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.REQUEST_STATUS, "All").commit();
                sp.edit().putString(ConstantSp.REQUEST_USER_TYPE, "Foreman").commit();
                title_menu.setText("Travelling History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        logout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        logout_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        logout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        price_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                openPriceDialog();
            }
        });

        price_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                openPriceDialog();
            }
        });

        price_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                openPriceDialog();
            }
        });

        profile_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_menu.setText("Profile");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new ProfileFragment()).addToBackStack("").commit();
            }
        });

        profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_menu.setText("Profile");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new ProfileFragment()).addToBackStack("").commit();
            }
        });

        profile_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_menu.setText("Profile");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new ProfileFragment()).addToBackStack("").commit();
            }
        });

        recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (sp.getString(ConstantSp.USERTYPE, "").equalsIgnoreCase("User")) {
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
                list.seteStatus("Approved");
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
            adapter = new HistoryAdapter(MainActivity.this, arrayList);
            recyclerView.setAdapter(adapter);

        } else {
            arrayList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
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
                list.seteStatus("Approved");
                list.setRequestStatus("Pending");
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
            adapter = new HistoryAdapter(MainActivity.this, arrayList);
            recyclerView.setAdapter(adapter);
        }

    }

    private void openPriceDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_price_layout);
        EditText price = dialog.findViewById(R.id.custom_price);
        price.setText(sp.getString(ConstantSp.PRICE, ""));
        TextView submit = dialog.findViewById(R.id.custom_price_submit);
        dialog.show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (price.getText().toString().trim().equalsIgnoreCase("")) {
                    price.setError("Price Required");
                } else {
                    sPrice = price.getText().toString();
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Price Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.app_name));
            builder.setMessage("Are You Sure Want To Exit!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (MainActivity.this, Manifest.permission.CALL_PHONE)) {

                Snackbar.make(MainActivity.this.findViewById(android.R.id.content),
                        "Please Grant Permissions to Download photo",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(
                                            new String[]{Manifest.permission
                                                    .CALL_PHONE},
                                            STORAGE_PERMISSION_CODE);
                                }
                            }
                        }).show();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                            new String[]{Manifest.permission
                                    .CALL_PHONE},
                            STORAGE_PERMISSION_CODE);
                }
            }
        } else {
            // write your logic code if permission already granted
        }
    }
}
