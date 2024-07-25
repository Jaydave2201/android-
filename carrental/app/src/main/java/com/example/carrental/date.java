package com.example.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class date extends AppCompatActivity {
    private EditText fromDateEditText, toDateEditText;
    private TextView welcomeText;
    private ImageView fromDateImageView, toDateImageView;
    private Button submitButton;
    private SharedPreferences sp;
    private SimpleDateFormat dateFormat;

    private Calendar fromDateCalendar, toDateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        welcomeText = findViewById(R.id.welcome);
        fromDateEditText = findViewById(R.id.FromDate);
        toDateEditText = findViewById(R.id.ToDate);
        fromDateImageView = findViewById(R.id.date);
        toDateImageView = findViewById(R.id.date1);
        submitButton = findViewById(R.id.submit);

        sp = getSharedPreferences(Constant.NAME, MODE_PRIVATE);
        welcomeText.setText("Welcome " + sp.getString(Constant.NAME, ""));

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();

        // Set click listeners for ImageView to show date picker dialogs
        fromDateImageView.setOnClickListener(v -> showDatePickerDialog(fromDateEditText, fromDateCalendar));
        toDateImageView.setOnClickListener(v -> showDatePickerDialog(toDateEditText, toDateCalendar));
        submitButton.setOnClickListener(this::onSubmitButtonClick);
    }

    private void onSubmitButtonClick(View view) {
        if (validateDateInput()) {
            Intent intent = new Intent(date.this, homepage.class);
            startActivity(intent);
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

        datePickerDialog.show();
    }
}
