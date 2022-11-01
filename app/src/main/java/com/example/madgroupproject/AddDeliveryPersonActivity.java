package com.example.madgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDeliveryPersonActivity extends AppCompatActivity {

    EditText name,contactNo,licenseNo,email;
    Button btnPersonAdd,btnBack;



    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_person);

        name = (EditText) findViewById(R.id.txtDpName);
        contactNo = (EditText) findViewById(R.id.txtDpPhone);
        licenseNo = (EditText) findViewById(R.id.txtDpLisence);
        email = (EditText) findViewById(R.id.txtDpEmail);

        btnPersonAdd = (Button) findViewById(R.id.btnPersonAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.txtDpName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.txtDpPhone,
                Patterns.PHONE,R.string.invalid_Phone);

        awesomeValidation.addValidation(this,R.id.txtDpLisence,
                RegexTemplate.NOT_EMPTY,R.string.invalid_license);

        awesomeValidation.addValidation(this,R.id.txtDpEmail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        btnPersonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    insertDeliveryPersonData();
                    clearDeliveryPersonData();
                }
                else{
                    Toast.makeText(getApplicationContext()
                            ,"Validation failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                back();

            }
        });

    }

    private void insertDeliveryPersonData(){

        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("contactNo",contactNo.getText().toString());
        map.put("licenseNo",licenseNo.getText().toString());
        map.put("email",email.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("DeliveryPersons").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddDeliveryPersonActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddDeliveryPersonActivity.this,"Error while inserting",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void clearDeliveryPersonData(){

        name.setText("");
        contactNo.setText("");
        licenseNo.setText("");
        email.setText("");
    }

    public void back(){
        Intent intent = new Intent(this,DeliveryPersonActivity1.class);
        startActivity(intent);
    }
}