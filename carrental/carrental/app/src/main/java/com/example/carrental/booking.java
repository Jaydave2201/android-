package com.example.carrental;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class booking extends AppCompatActivity implements PaymentResultListener {
    private EditText fromDateEditText, toDateEditText, customername, customernumber;
    private ImageView fromDateImageView, toDateImageView;
    private Button submitButton;
    private CheckBox chek;
    private SimpleDateFormat dateFormat;

    TextView cost;

    private Calendar fromDateCalendar, toDateCalendar;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        // Initialize SharedPreferences
        sp = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        // Initialize views
        fromDateEditText = findViewById(R.id.fromDate);
        toDateEditText = findViewById(R.id.toDate);
        fromDateImageView = findViewById(R.id.fromDateImageView);
        toDateImageView = findViewById(R.id.toDateImageView);
        chek = findViewById(R.id.termsCheckBox);
        submitButton = findViewById(R.id.submitButton);
        customername = findViewById(R.id.customerName);
        customernumber = findViewById(R.id.customerNumber);

        cost = findViewById(R.id.cost);
        cost.setText(sp.getString(Constant.CATEGORY_PRICE,""));

        // Initialize DateFormat and Calendar
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();

        // Set values from SharedPreferences
        String name = sp.getString(Constant.NAME, "");
        String contact = sp.getString(Constant.CONTACT, "");
        customername.setText(name);
        customernumber.setText(contact);

        // Initialize Razorpay Checkout
        Checkout.preload(getApplicationContext());

        // Set click listeners for ImageView to show date picker dialogs
        fromDateImageView.setOnClickListener(v -> showDatePickerDialog(fromDateEditText, fromDateCalendar));
        toDateImageView.setOnClickListener(v -> showDatePickerDialog(toDateEditText, toDateCalendar));

        // Set click listener for submit button
        submitButton.setOnClickListener(this::onSubmitButtonClick);
    }

    private void onSubmitButtonClick(View view) {
        if (!chek.isChecked()) {
            Toast.makeText(this, "Please accept terms & conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        if (validateDateInput()) {
            // Start Razorpay payment process
            startPayment(sp.getString(Constant.CONTACT, ""));
        }
    }

    private boolean validateDateInput() {
        String fromDate = fromDateEditText.getText().toString();
        String toDate = toDateEditText.getText().toString();
        boolean isValid = true;

        if (fromDate.isEmpty()) {
            fromDateEditText.setError("Please select a from date");
            isValid = false;
        } else if (!isValidDateFormat(fromDate)) {
            fromDateEditText.setError("Invalid date format. Use dd/MM/yyyy");
            isValid = false;
        }

        if (toDate.isEmpty()) {
            toDateEditText.setError("Please select a to date");
            isValid = false;
        } else if (!isValidDateFormat(toDate)) {
            toDateEditText.setError("Invalid date format. Use dd/MM/yyyy");
            isValid = false;
        } else if (isDateBefore(fromDate, toDate)) {
            toDateEditText.setError("To date cannot be earlier than from date");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidDateFormat(String date) {
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDateBefore(String fromDate, String toDate) {
        try {
            return dateFormat.parse(toDate).before(dateFormat.parse(fromDate));
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private void showDatePickerDialog(final EditText editText, final Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                    String selectedDate = dateFormat.format(calendar.getTime());
                    editText.setText(selectedDate);
                },
                year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void startPayment(String userMobileNo) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_BMlkloi7Q5AF82");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "CarRental");
            options.put("description", "Car Booking Payment");
            options.put("currency", "INR");
            options.put("amount", Integer.parseInt(sp.getString(Constant.CATEGORY_PRICE,""))*100); // Amount in paise (e.g., 10000 paise = 100 INR)
            options.put("prefill.contact", userMobileNo); // Optional

            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentId) {
        Toast.makeText(this, "Payment Successful: ", Toast.LENGTH_SHORT).show();
        // Proceed with booking
        Intent intent = new Intent(booking.this, succesful.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
    }
}
