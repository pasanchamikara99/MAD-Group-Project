package com.example.madgroupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class DeleteCategory extends AppCompatActivity {

    Button deleteCategoryBtn;
    TextView deleteCategoryID,deleteCategoryTextTitle;
    ImageView deleteCategoryImageView;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        deleteCategoryBtn = findViewById(R.id.DeleteCategoryBtn);
        deleteCategoryID = findViewById(R.id.deleteCategoryTxtID);
        deleteCategoryTextTitle = findViewById(R.id.txtDeleteCategoryTitle);
        deleteCategoryImageView = findViewById(R.id.deleteCategoryImageView);


        deleteCategoryID.setText(getIntent().getStringExtra("id"));
        deleteCategoryTextTitle.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).placeholder(R.drawable.logo).into(deleteCategoryImageView);


        String deleteCategoryId = deleteCategoryID.getText().toString();

       deleteCategoryBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               builder = new AlertDialog.Builder(DeleteCategory.this);

               builder.setMessage(R.string.alertDialogMessage).setTitle(R.string.alertDialogTitle);

               builder.setMessage("Deleted item can't undo !!")
                       .setCancelable(false)
                       .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               DatabaseReference def = FirebaseDatabase.getInstance().getReference().child("Category").child(deleteCategoryId);
                               def.removeValue();
                               Toast.makeText(getApplicationContext(), " Category item deleted Successfully ", Toast.LENGTH_LONG).show();
                               startActivity(new Intent(DeleteCategory.this,CategoryPage.class));
                               finish();



                           }
                       })
                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               //  Action for 'NO' Button
                               dialog.cancel();
                               //Toast.makeText(holder.categoryID.getContext(),"cancel", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(DeleteCategory.this,CategoryPage.class));
                               finish();
                           }
                       });



               //Creating dialog box
               AlertDialog alert = builder.create();
               //Setting the title manually
               alert.show();

           }
       });



    }


}