package com.example.login;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
public class Commanmethod {
    public Commanmethod(View view, String mess) {
        Snackbar.make(view, mess, Snackbar.LENGTH_LONG).show();
    }
}