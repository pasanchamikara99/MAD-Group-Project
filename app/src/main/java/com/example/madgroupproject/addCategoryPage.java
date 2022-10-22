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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addCategoryPage extends AppCompatActivity {


    private Button uploadBtn;
    private ImageView imageView;
    public FirebaseDatabase database;
    public FirebaseStorage firebaseStorage;
    private Uri imageUri;
    private EditText menuID,menuTitle;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_add_category_page);

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        uploadBtn = findViewById(R.id.addCategoryBtn);
        imageView = findViewById(R.id.addCategoryImage);
        menuTitle = findViewById(R.id.editTxtCategoryTitle);
        menuID = findViewById(R.id.editTxtcategoryID);


        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please wait.......");
        dialog.setCancelable(false);
        dialog.setTitle("Category Uploading ");
        dialog.setCanceledOnTouchOutside(false);



        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();


                final StorageReference reference = firebaseStorage.getReference().child("Category").child(System.currentTimeMillis() + "");

                reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Category categoryObj = new Category();

                                String id = menuID.getText().toString().trim();
                                String title = menuTitle.getText().toString().trim();

                                if(!id.isEmpty() && !title.isEmpty()){
                                    categoryObj.setId(id);
                                    categoryObj.setTitle(title);
                                    categoryObj.setUrl(uri.toString());

                                    DatabaseReference dbRef = database.getReference().child("Category");


                                    dbRef.child(id).setValue(categoryObj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            Toast.makeText(addCategoryPage.this,"Upload category successfully",Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                            startActivity(new Intent(addCategoryPage.this,CategoryPage.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog.dismiss();
                                            Toast.makeText(addCategoryPage.this,"Upload Category failed !!! ",Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }else{
                                    dialog.dismiss();
                                    Toast.makeText(addCategoryPage.this,"please fill all the fields",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });



            }
        });




        imageView.setOnClickListener(new View.OnClickListener() {
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

            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

    }


}