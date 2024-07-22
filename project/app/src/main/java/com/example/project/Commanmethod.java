package com.example.project;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
public class Commanmethod {
    public Commanmethod(View view, String mess) {
        Snackbar.make(view, mess, Snackbar.LENGTH_LONG).show();
    }
    Commanmethod(Context context,String mess){
        Toast.makeText(context,mess,Toast.LENGTH_LONG).show();
    }
}