package com.example.madgroupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class DeliveryPersonActivity1 extends AppCompatActivity {
    RecyclerView recyclerView;

    DeliveryPersonMainAdapter mainAdapter;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_person1);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DeliveryPersonModel> options =
                new FirebaseRecyclerOptions.Builder<DeliveryPersonModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DeliveryPersons"), DeliveryPersonModel.class)
                        .build();

        mainAdapter = new DeliveryPersonMainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        floatingActionButton=(FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddDeliveryPersonActivity.class));
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}