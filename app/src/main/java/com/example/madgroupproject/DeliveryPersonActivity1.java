package com.example.madgroupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

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

//    //search
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search_person,menu);
//
//        MenuItem item = menu.findItem(R.id.searchPerson);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                textSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                textSearch(query);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    //search delivery person by name
//
//    private void textSearch(String str)
//    {
//        FirebaseRecyclerOptions<DeliveryPersonModel> options =
//                new FirebaseRecyclerOptions.Builder<DeliveryPersonModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DeliveryPersons").orderByChild("name").startAt(str).endAt(str+"-"), DeliveryPersonModel.class)
//                        .build();
//
//        mainAdapter = new DeliveryPersonMainAdapter(options);
//        mainAdapter.startListening();
//        recyclerView.setAdapter(mainAdapter);
//    }
}