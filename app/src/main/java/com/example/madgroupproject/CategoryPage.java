package com.example.madgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryPage extends AppCompatActivity {

    Button navigateAddCategory,categoryDelete;
    RecyclerView recyclerView;
    ArrayList<Category> recycleList;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        navigateAddCategory = findViewById(R.id.btnAddCategory);


        recyclerView = findViewById(R.id.recyclerView);
        recycleList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();



        ProjectAdepter recycleAdapter = new ProjectAdepter(recycleList,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recycleAdapter);

        firebaseDatabase.getReference().child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Category categoryModel = dataSnapshot.getValue(Category.class);
                    recycleList.add(categoryModel);
                }

                recycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        navigateAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryPage.this,addCategoryPage.class));

            }
        });

    }
}