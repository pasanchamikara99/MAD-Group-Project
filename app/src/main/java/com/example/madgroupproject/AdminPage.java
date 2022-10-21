package com.example.madgroupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    Button btnCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        btnCategory = findViewById(R.id.btnCategory);


        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category();
            }
        });
    }


    private void category(){

        startActivity(new Intent(AdminPage.this,CategoryPage.class));

    }
}