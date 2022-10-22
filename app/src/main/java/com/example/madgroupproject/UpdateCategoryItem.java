package com.example.madgroupproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class UpdateCategoryItem extends AppCompatActivity {

    private TextView updateCategoryTextid;
    private EditText updateCategoryTextTitle;
    private ImageView updateCategoryImageView;
    private Button updateBtn;
    public FirebaseDatabase database;
    public FirebaseStorage firebaseStorage;
    private Uri imageUpdateUri;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();



        updateCategoryTextid = findViewById(R.id.updateCategoryTxtID);
        updateCategoryTextTitle = findViewById(R.id.updateCategoryTxtTitle);
        updateCategoryImageView = findViewById(R.id.updateCategoryImageView);


        updateCategoryTextid.setText(getIntent().getStringExtra("id"));
        updateCategoryTextTitle.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).placeholder(R.drawable.logo).into(updateCategoryImageView);





        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please wait.......");
        dialog.setCancelable(false);
        dialog.setTitle("Category Uploading ");
        dialog.setCanceledOnTouchOutside(false);



        updateBtn = findViewById(R.id.updateCategoryBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
                final StorageReference reference = firebaseStorage.getReference().child("Category").child(System.currentTimeMillis() + "");

                reference.putFile(imageUpdateUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Category categoryObj = new Category();

                                String id = updateCategoryTextid.getText().toString().trim();
                                String title = updateCategoryTextTitle.getText().toString().trim();

                                if(!id.isEmpty() && !title.isEmpty()){
                                    categoryObj.setId(id);
                                    categoryObj.setTitle(title);
                                    categoryObj.setUrl(uri.toString());

                                    DatabaseReference dbRef = database.getReference().child("Category");


                                    dbRef.child(id).setValue(categoryObj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            Toast.makeText(UpdateCategoryItem.this,"Update category successfully",Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                            startActivity(new Intent(UpdateCategoryItem.this,CategoryPage.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog.dismiss();
                                            Toast.makeText(UpdateCategoryItem.this,"Update Category failed !!! ",Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }else{
                                    dialog.dismiss();
                                    Toast.makeText(UpdateCategoryItem.this,"please fill all the fields",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                 }
              });


            }
        });

        updateCategoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){

            imageUpdateUri = data.getData();
            updateCategoryImageView.setImageURI(imageUpdateUri);
        }

    }

}