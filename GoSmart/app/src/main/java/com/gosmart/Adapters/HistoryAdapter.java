package com.gosmart.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.gosmart.R;
import com.gosmart.SetGet.HistoryList;
import com.gosmart.Utils.ConstantSp;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<HistoryList> vehicleLists;
    SharedPreferences sp;
    String sId, sNoPerson, sContact, sHistoryId, sStatus, sRequestStatus, sTotalDistance, sPrice, sTotalPrice,sPaymentMethod,sTransactionId;
    int iPosition;
    Dialog dialog;

    public HistoryAdapter(Context mainActivity, ArrayList<HistoryList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        TextView name, model, color, year, ownerName, customerName, noOfPassenger, customerContact, accept, reject, join, requestStaus, totalDistance, totalAmount, submitDistance, payNow,maxPerson;
        LinearLayout customerContactLayout, customerLayout, acceptRejectLayout, requestStatusLayout, totalDistanceLayout, totalAmountLayout;

        public MyViewHolder(View view) {
            super(view);
            //iv = (ImageView) view.findViewById(R.id.custom_category_quiz_iv);
            name = (TextView) view.findViewById(R.id.custom_history_name);
            model = (TextView) view.findViewById(R.id.custom_history_model);
            color = (TextView) view.findViewById(R.id.custom_history_color);
            year = (TextView) view.findViewById(R.id.custom_history_year);
            iv = (ImageView) view.findViewById(R.id.custom_history_image);
            join = view.findViewById(R.id.custom_history_join);
            ownerName = view.findViewById(R.id.custom_history_owner_name);
            customerName = view.findViewById(R.id.custom_history_customer_name);
            noOfPassenger = view.findViewById(R.id.custom_history_no_passenger);
            customerContact = view.findViewById(R.id.custom_history_contact);
            accept = view.findViewById(R.id.custom_history_accept);
            reject = view.findViewById(R.id.custom_history_reject);
            join = view.findViewById(R.id.custom_history_join);
            customerContactLayout = view.findViewById(R.id.custom_history_customer_contact_layout);
            customerLayout = view.findViewById(R.id.custom_history_customer_layout);
            acceptRejectLayout = view.findViewById(R.id.custom_history_accept_reject_layout);

            requestStaus = view.findViewById(R.id.custom_history_request_status);
            requestStatusLayout = view.findViewById(R.id.custom_history_request_status_layout);

            totalDistance = view.findViewById(R.id.custom_history_distance);
            totalDistanceLayout = view.findViewById(R.id.custom_history_distance_layout);

            totalAmount = view.findViewById(R.id.custom_history_payment);
            totalAmountLayout = view.findViewById(R.id.custom_history_payment_layout);

            submitDistance = view.findViewById(R.id.custom_history_submit_distance);
            payNow = view.findViewById(R.id.custom_history_submit_paynow);

            maxPerson = view.findViewById(R.id.custom_history_max_person);

        }
    }

    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_history, parent, false);
        return new HistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        /*if (vehicleLists.get(position).getStartFlag() == 0) {
            holder.start.setText("Start Journey");
        } else {
            holder.start.setText("Stop Journey");
        }*/
        if (sp.getString(ConstantSp.ID, "").equalsIgnoreCase(vehicleLists.get(position).getUserId())) {
            if (vehicleLists.get(position).geteStatus().equalsIgnoreCase("start")) {
                holder.join.setVisibility(View.GONE);
                holder.customerLayout.setVisibility(View.GONE);
                holder.customerContactLayout.setVisibility(View.GONE);
                holder.acceptRejectLayout.setVisibility(View.GONE);
            } else {
                holder.join.setVisibility(View.GONE);
                holder.customerLayout.setVisibility(View.VISIBLE);
                holder.customerContactLayout.setVisibility(View.VISIBLE);
                if (vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("Pending")) {
                    holder.acceptRejectLayout.setVisibility(View.VISIBLE);
                    holder.totalDistanceLayout.setVisibility(View.GONE);
                    holder.totalAmountLayout.setVisibility(View.GONE);
                    holder.submitDistance.setVisibility(View.GONE);
                    holder.payNow.setVisibility(View.GONE);
                } else if (vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("Accept")) {
                    holder.acceptRejectLayout.setVisibility(View.GONE);
                    holder.totalDistanceLayout.setVisibility(View.GONE);
                    holder.totalAmountLayout.setVisibility(View.GONE);
                    holder.submitDistance.setVisibility(View.VISIBLE);
                    holder.payNow.setVisibility(View.GONE);
                } else if (vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("PaymentRequest")) {
                    holder.acceptRejectLayout.setVisibility(View.GONE);
                    holder.totalDistanceLayout.setVisibility(View.VISIBLE);
                    holder.totalAmountLayout.setVisibility(View.VISIBLE);
                    holder.submitDistance.setVisibility(View.GONE);
                    holder.payNow.setVisibility(View.GONE);
                } else {
                    if(vehicleLists.get(position).geteStatus().equalsIgnoreCase("completed") && vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("Reject")){
                        holder.acceptRejectLayout.setVisibility(View.GONE);
                        holder.totalDistanceLayout.setVisibility(View.GONE);
                        holder.totalAmountLayout.setVisibility(View.GONE);
                    }
                    else {
                        holder.acceptRejectLayout.setVisibility(View.GONE);
                        holder.totalDistanceLayout.setVisibility(View.VISIBLE);
                        holder.totalAmountLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        } else {
            if (vehicleLists.get(position).geteStatus().equalsIgnoreCase("start")) {
                holder.join.setVisibility(View.VISIBLE);
                holder.customerLayout.setVisibility(View.GONE);
                holder.customerContactLayout.setVisibility(View.GONE);
                holder.acceptRejectLayout.setVisibility(View.GONE);
            } else {
                holder.join.setVisibility(View.GONE);
                holder.customerLayout.setVisibility(View.VISIBLE);
                holder.customerContactLayout.setVisibility(View.VISIBLE);
                if (vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("Pending")) {
                    holder.acceptRejectLayout.setVisibility(View.GONE);
                    holder.totalDistanceLayout.setVisibility(View.GONE);
                    holder.totalAmountLayout.setVisibility(View.GONE);
                    holder.submitDistance.setVisibility(View.GONE);
                    holder.payNow.setVisibility(View.GONE);
                } else if (vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("Accept")) {
                    holder.acceptRejectLayout.setVisibility(View.GONE);
                    holder.totalDistanceLayout.setVisibility(View.GONE);
                    holder.totalAmountLayout.setVisibility(View.GONE);
                    holder.submitDistance.setVisibility(View.GONE);
                    holder.payNow.setVisibility(View.GONE);
                } else if (vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("PaymentRequest")) {
                    holder.acceptRejectLayout.setVisibility(View.GONE);
                    holder.totalDistanceLayout.setVisibility(View.VISIBLE);
                    holder.totalAmountLayout.setVisibility(View.VISIBLE);
                    holder.submitDistance.setVisibility(View.GONE);
                    holder.payNow.setVisibility(View.VISIBLE);
                } else {
                    holder.submitDistance.setVisibility(View.GONE);
                    holder.payNow.setVisibility(View.GONE);
                    if(vehicleLists.get(position).geteStatus().equalsIgnoreCase("completed") && vehicleLists.get(position).getRequestStatus().equalsIgnoreCase("Reject")){
                        holder.acceptRejectLayout.setVisibility(View.GONE);
                        holder.totalDistanceLayout.setVisibility(View.GONE);
                        holder.totalAmountLayout.setVisibility(View.GONE);
                    }
                    else {
                        holder.acceptRejectLayout.setVisibility(View.GONE);
                        holder.totalDistanceLayout.setVisibility(View.VISIBLE);
                        holder.totalAmountLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        holder.requestStaus.setText(Html.fromHtml(vehicleLists.get(position).getRequestStatus()));
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.model.setText(Html.fromHtml(vehicleLists.get(position).getModel()));
        holder.color.setText(Html.fromHtml(vehicleLists.get(position).getColor()));
        holder.year.setText(Html.fromHtml(vehicleLists.get(position).getYear()));
        holder.ownerName.setText(Html.fromHtml(vehicleLists.get(position).getOwnerName()));
        holder.customerName.setText(Html.fromHtml(vehicleLists.get(position).getCustomerName()));
        holder.customerContact.setText(Html.fromHtml(vehicleLists.get(position).getContactNo()));
        holder.noOfPassenger.setText(Html.fromHtml(vehicleLists.get(position).getPassengerNoPerson()));
        holder.totalDistance.setText(Html.fromHtml(vehicleLists.get(position).getTotalDistance() + " KM"));

        holder.maxPerson.setText(Html.fromHtml(vehicleLists.get(position).getNoPerson()));

        if (vehicleLists.get(position).getPaymentType().equalsIgnoreCase("")) {
            holder.totalAmount.setText(Html.fromHtml(context.getResources().getString(R.string.price_symbol) + vehicleLists.get(position).getTotalPrice()));
            holder.payNow.setText("Pay Now "+context.getResources().getString(R.string.price_symbol) + vehicleLists.get(position).getTotalPrice());
        } else {
            if (vehicleLists.get(position).getPaymentType().equalsIgnoreCase("Cash")) {
                holder.totalAmount.setText(Html.fromHtml(context.getResources().getString(R.string.price_symbol) + vehicleLists.get(position).getTotalPrice() + " (" + vehicleLists.get(position).getPaymentType() + ")"));
            } else {
                holder.totalAmount.setText(Html.fromHtml(context.getResources().getString(R.string.price_symbol) + vehicleLists.get(position).getTotalPrice() + " (" + vehicleLists.get(position).getPaymentType() + ") (" + vehicleLists.get(position).getTransactionId() + ")"));
            }
        }

        holder.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPosition = position;
                sId = vehicleLists.get(position).getVehicleId();
                sHistoryId = vehicleLists.get(position).getId();
                int iNoPerson = Integer.parseInt(vehicleLists.get(position).getNoPerson());
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_join_journey);
                EditText noPerson = dialog.findViewById(R.id.custom_join_journey_no);
                EditText contactNo = dialog.findViewById(R.id.custom_join_journey_contact);
                TextView submit = dialog.findViewById(R.id.custom_join_journey_submit);
                dialog.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (noPerson.getText().toString().trim().equalsIgnoreCase("")) {
                            noPerson.setError("No. Of Person Required");
                        } else if (contactNo.getText().toString().trim().equalsIgnoreCase("")) {
                            contactNo.setError("Contact No. Required");
                        } else if (contactNo.getText().toString().length() < 10 || contactNo.getText().toString().length() > 10) {
                            contactNo.setError("Valid Contact No. Required");
                        } else {
                            if(iNoPerson>Integer.parseInt(noPerson.getText().toString())){
                                sNoPerson = noPerson.getText().toString();
                                sContact = contactNo.getText().toString();
                                sStatus = "join";
                                sRequestStatus = "Pending";
                                Toast.makeText(context, "Start Successfully", Toast.LENGTH_SHORT).show();
                                vehicleLists.remove(iPosition);
                                notifyDataSetChanged();
                            }
                            else{
                                noPerson.setError("No. Of Person Max."+iNoPerson+" Required");
                            }
                        }
                    }
                });
            }
        });

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPosition = position;
                sId = vehicleLists.get(position).getVehicleId();
                sHistoryId = vehicleLists.get(position).getId();
                sStatus = "join";
                sRequestStatus = "Accept";

                Toast.makeText(context, "Accepted Successfully", Toast.LENGTH_SHORT).show();
                HistoryList list = new HistoryList();
                list.setId(vehicleLists.get(iPosition).getId());
                list.setVehicleId(vehicleLists.get(iPosition).getVehicleId());
                list.setVehicleUserId(vehicleLists.get(iPosition).getVehicleUserId());
                list.setName(vehicleLists.get(iPosition).getName());
                list.setType(vehicleLists.get(iPosition).getType());
                list.setModel(vehicleLists.get(iPosition).getModel());
                list.setModelSP(vehicleLists.get(iPosition).getModelSP());
                list.setColor(vehicleLists.get(iPosition).getColor());
                list.setYear(vehicleLists.get(iPosition).getYear());
                list.setVin(vehicleLists.get(iPosition).getVin());
                list.setVehiclecreated_date(vehicleLists.get(iPosition).getVehiclecreated_date());
                list.setUserId(vehicleLists.get(iPosition).getUserId());
                list.setOwnerName(vehicleLists.get(iPosition).getOwnerName());
                list.setNoPerson(vehicleLists.get(iPosition).getNoPerson());
                list.setPassengerId(vehicleLists.get(iPosition).getPassengerId());
                list.setCustomerName(vehicleLists.get(iPosition).getCustomerName());
                list.setPassengerNoPerson(vehicleLists.get(iPosition).getPassengerNoPerson());
                list.setContactNo(vehicleLists.get(iPosition).getContactNo());
                list.seteStatus(sStatus);
                list.setRequestStatus(sRequestStatus);
                list.setCreated_date(vehicleLists.get(iPosition).getCreated_date());

                list.setOwnerPrice(vehicleLists.get(iPosition).getOwnerPrice());
                list.setTotalDistance(vehicleLists.get(iPosition).getTotalDistance());
                list.setPrice(vehicleLists.get(iPosition).getPrice());
                list.setTotalPrice(vehicleLists.get(iPosition).getTotalPrice());
                list.setPaymentType(vehicleLists.get(iPosition).getPaymentType());
                list.setTransactionId(vehicleLists.get(iPosition).getTransactionId());
                vehicleLists.set(iPosition, list);
                notifyDataSetChanged();
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPosition = position;
                sId = vehicleLists.get(position).getVehicleId();
                sHistoryId = vehicleLists.get(position).getId();
                sStatus = "completed";
                sRequestStatus = "Reject";
                Toast.makeText(context, "Rejected Successfully", Toast.LENGTH_SHORT).show();
                HistoryList list = new HistoryList();
                list.setId(vehicleLists.get(iPosition).getId());
                list.setVehicleId(vehicleLists.get(iPosition).getVehicleId());
                list.setVehicleUserId(vehicleLists.get(iPosition).getVehicleUserId());
                list.setName(vehicleLists.get(iPosition).getName());
                list.setType(vehicleLists.get(iPosition).getType());
                list.setModel(vehicleLists.get(iPosition).getModel());
                list.setModelSP(vehicleLists.get(iPosition).getModelSP());
                list.setColor(vehicleLists.get(iPosition).getColor());
                list.setYear(vehicleLists.get(iPosition).getYear());
                list.setVin(vehicleLists.get(iPosition).getVin());
                list.setVehiclecreated_date(vehicleLists.get(iPosition).getVehiclecreated_date());
                list.setUserId(vehicleLists.get(iPosition).getUserId());
                list.setOwnerName(vehicleLists.get(iPosition).getOwnerName());
                list.setNoPerson(vehicleLists.get(iPosition).getNoPerson());
                list.setPassengerId(vehicleLists.get(iPosition).getPassengerId());
                list.setCustomerName(vehicleLists.get(iPosition).getCustomerName());
                list.setPassengerNoPerson(vehicleLists.get(iPosition).getPassengerNoPerson());
                list.setContactNo(vehicleLists.get(iPosition).getContactNo());
                list.seteStatus(sStatus);
                list.setRequestStatus(sRequestStatus);
                list.setCreated_date(vehicleLists.get(iPosition).getCreated_date());

                list.setOwnerPrice(vehicleLists.get(iPosition).getOwnerPrice());
                list.setTotalDistance(vehicleLists.get(iPosition).getTotalDistance());
                list.setPrice(vehicleLists.get(iPosition).getPrice());
                list.setTotalPrice(vehicleLists.get(iPosition).getTotalPrice());
                list.setPaymentType(vehicleLists.get(iPosition).getPaymentType());
                list.setTransactionId(vehicleLists.get(iPosition).getTransactionId());
                vehicleLists.set(iPosition, list);
                notifyDataSetChanged();
            }
        });

        holder.submitDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPosition = position;
                sId = vehicleLists.get(position).getVehicleId();
                sHistoryId = vehicleLists.get(position).getId();
                sPrice = vehicleLists.get(position).getOwnerPrice();
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_distance);
                EditText totalDistance = dialog.findViewById(R.id.custom_total_distance);
                TextView submit = dialog.findViewById(R.id.custom_distance_submit);
                dialog.show();

                totalDistance.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        int iTotalPrice = 0;
                        if (s.toString().trim().equalsIgnoreCase("") || s.toString().equalsIgnoreCase("0")) {
                            iTotalPrice = 0;
                            sTotalPrice = "0";
                            submit.setText("Payment Request For " + context.getResources().getString(R.string.price_symbol) + sTotalPrice);
                        } else {
                            iTotalPrice = Integer.parseInt(sPrice) * Integer.parseInt(s.toString());
                            sTotalPrice = String.valueOf(iTotalPrice);
                            submit.setText("Payment Request For " + context.getResources().getString(R.string.price_symbol) + sTotalPrice);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (totalDistance.getText().toString().trim().equalsIgnoreCase("") || totalDistance.getText().toString().trim().equals("0")) {
                            totalDistance.setError("TotalDistance Required");
                        } else {
                            sTotalDistance = totalDistance.getText().toString();
                            sRequestStatus = "PaymentRequest";
                            Toast.makeText(context, "Payment Request Successfully", Toast.LENGTH_SHORT).show();
                            HistoryList list = new HistoryList();
                            list.setId(vehicleLists.get(iPosition).getId());
                            list.setVehicleId(vehicleLists.get(iPosition).getVehicleId());
                            list.setVehicleUserId(vehicleLists.get(iPosition).getVehicleUserId());
                            list.setName(vehicleLists.get(iPosition).getName());
                            list.setType(vehicleLists.get(iPosition).getType());
                            list.setModel(vehicleLists.get(iPosition).getModel());
                            list.setModelSP(vehicleLists.get(iPosition).getModelSP());
                            list.setColor(vehicleLists.get(iPosition).getColor());
                            list.setYear(vehicleLists.get(iPosition).getYear());
                            list.setVin(vehicleLists.get(iPosition).getVin());
                            list.setVehiclecreated_date(vehicleLists.get(iPosition).getVehiclecreated_date());
                            list.setUserId(vehicleLists.get(iPosition).getUserId());
                            list.setOwnerName(vehicleLists.get(iPosition).getOwnerName());
                            list.setNoPerson(vehicleLists.get(iPosition).getNoPerson());
                            list.setPassengerId(vehicleLists.get(iPosition).getPassengerId());
                            list.setCustomerName(vehicleLists.get(iPosition).getCustomerName());
                            list.setPassengerNoPerson(vehicleLists.get(iPosition).getPassengerNoPerson());
                            list.setContactNo(vehicleLists.get(iPosition).getContactNo());
                            list.seteStatus(vehicleLists.get(iPosition).geteStatus());
                            list.setRequestStatus(sRequestStatus);
                            list.setCreated_date(vehicleLists.get(iPosition).getCreated_date());

                            list.setOwnerPrice(vehicleLists.get(iPosition).getOwnerPrice());
                            list.setTotalDistance(sTotalDistance);
                            list.setPrice(sPrice);
                            list.setTotalPrice(sTotalPrice);
                            list.setPaymentType(vehicleLists.get(iPosition).getPaymentType());
                            list.setTransactionId(vehicleLists.get(iPosition).getTransactionId());
                            vehicleLists.set(iPosition, list);
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        });

        holder.payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPosition = position;
                sId = vehicleLists.get(position).getVehicleId();
                sHistoryId = vehicleLists.get(position).getId();
                sTotalPrice = vehicleLists.get(position).getTotalPrice();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Select Payment Method");
                builder.setPositiveButton("Online", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sp.edit().putString(ConstantSp.HISTORY_ID,sHistoryId).commit();
                        sp.edit().putString(ConstantSp.TOTAL_PRICE,sTotalPrice).commit();
                        //context.startActivity(new Intent(context, PayNowActivity.class));
                    }
                });
                builder.setNeutralButton("Cash", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sPaymentMethod = "Cash";
                        sTransactionId = "";
                        sRequestStatus = "Paid";

                        Toast.makeText(context, "Payment Successfully", Toast.LENGTH_SHORT).show();
                        HistoryList list = new HistoryList();
                        list.setId(vehicleLists.get(iPosition).getId());
                        list.setVehicleId(vehicleLists.get(iPosition).getVehicleId());
                        list.setVehicleUserId(vehicleLists.get(iPosition).getVehicleUserId());
                        list.setName(vehicleLists.get(iPosition).getName());
                        list.setType(vehicleLists.get(iPosition).getType());
                        list.setModel(vehicleLists.get(iPosition).getModel());
                        list.setModelSP(vehicleLists.get(iPosition).getModelSP());
                        list.setColor(vehicleLists.get(iPosition).getColor());
                        list.setYear(vehicleLists.get(iPosition).getYear());
                        list.setVin(vehicleLists.get(iPosition).getVin());
                        list.setVehiclecreated_date(vehicleLists.get(iPosition).getVehiclecreated_date());
                        list.setUserId(vehicleLists.get(iPosition).getUserId());
                        list.setOwnerName(vehicleLists.get(iPosition).getOwnerName());
                        list.setNoPerson(vehicleLists.get(iPosition).getNoPerson());
                        list.setPassengerId(vehicleLists.get(iPosition).getPassengerId());
                        list.setCustomerName(vehicleLists.get(iPosition).getCustomerName());
                        list.setPassengerNoPerson(vehicleLists.get(iPosition).getPassengerNoPerson());
                        list.setContactNo(vehicleLists.get(iPosition).getContactNo());
                        list.seteStatus(vehicleLists.get(iPosition).geteStatus());
                        list.setRequestStatus(sRequestStatus);
                        list.setCreated_date(vehicleLists.get(iPosition).getCreated_date());

                        list.setOwnerPrice(vehicleLists.get(iPosition).getOwnerPrice());
                        list.setTotalDistance(vehicleLists.get(iPosition).getTotalDistance());
                        list.setPrice(vehicleLists.get(iPosition).getPrice());
                        list.setTotalPrice(vehicleLists.get(iPosition).getTotalPrice());
                        list.setPaymentType(sPaymentMethod);
                        list.setTransactionId(sTransactionId);
                        vehicleLists.set(iPosition, list);
                        notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }

}
