package com.example.madgroupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminPage extends AppCompatActivity {

    Button btnCategory;
    ImageButton imgButton1,imgButton2,imgButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        imgButton1 =findViewById(R.id.imgBtnDelivery);
        imgButton2 =findViewById(R.id.imgBtnCategory);
        imgButton3 =findViewById(R.id.imgBtnFooditems);


//        btnCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                category();
//            }
//        });

        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, DeliveryPersonActivity1.class);
                startActivity(intent);
            }
        });

        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, CategoryPage.class);
                startActivity(intent);
            }
        });
        imgButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, Food_main.class);
                startActivity(intent);
            }
        });
    }


//    private void category(){
//        startActivity(new Intent(AdminPage.this,CategoryPage.class));
//    }
}