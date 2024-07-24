package com.example.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class date extends AppCompatActivity {
    private static final String TAG = "date";
    private EditText fromDateEditText, toDateEditText;
    private TextView welcomeText;
    private Button submitButton;
    private SharedPreferences sp;
    private SimpleDateFormat dateFormat;
    private static final String SHARED_PREFERENCES_NAME = "mysp";
    private static final String KEY_NAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        // Initialize views
        welcomeText = findViewById(R.id.welcome);
        fromDateEditText = findViewById(R.id.FromDate);
        toDateEditText = findViewById(R.id.ToDate);
        submitButton = findViewById(R.id.submit);

        // Initialize SharedPreferences and get username
        sp = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        String username = sp.getString(KEY_NAME, "User");
        welcomeText.setText("Welcome " + username);

        // Initialize date format
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Set click listeners
        fromDateEditText.setOnClickListener(v -> showDatePickerDialog(fromDateEditText));
        toDateEditText.setOnClickListener(v -> showDatePickerDialog(toDateEditText));
        submitButton.setOnClickListener(this::onSubmitButtonClick);
    }

    private void onSubmitButtonClick(View view) {
        // Validate date inputs
        if (validateDateInput()) {
            // Navigate to homepage activity
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

    private void showDatePickerDialog(final EditText editText) {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the date and set it on the EditText
                    String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                    editText.setText(selectedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }
}
