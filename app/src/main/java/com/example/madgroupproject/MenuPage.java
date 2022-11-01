package com.example.madgroupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPage extends AppCompatActivity {

    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);


        logoutBtn = findViewById(R.id.logoutbtn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManagement session = new sessionManagement(MenuPage.this);
                session.removeSession();

                startActivity(new Intent(MenuPage.this,LoginPage.class));
                finish();
            }
        });

    }
}