package com.example.madgroupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CartActivity2 extends AppCompatActivity {


    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

    }
}