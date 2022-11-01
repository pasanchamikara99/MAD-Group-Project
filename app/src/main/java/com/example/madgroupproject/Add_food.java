package com.example.madgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_food extends AppCompatActivity {

    EditText name,description,price,id,purl;
    Button submit,back;
    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        name=(EditText) findViewById(R.id.add_name);
        id=(EditText) findViewById(R.id.add_id);
        description=(EditText) findViewById(R.id.add_des);
        price=(EditText) findViewById(R.id.add_price);
        purl=(EditText) findViewById(R.id.add_purl);

        back=(Button) findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Food_main.class));
                finish();
            }
        });

        submit=(Button) findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                processinsert();

            }
        };



    });

    }

    private void processinsert() {
        Map<String,Object> map= new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("price",price.getText().toString());
        map.put("id",id.getText().toString());
        map.put("description",description.getText().toString());
        map.put("purl",purl.getText().toString());



        FirebaseDatabase.getInstance().getReference().child("Food").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        name.setText("");;
                        price.setText("");
                        id.setText("");
                        description.setText("");
                        purl.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Submit Failed",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean CheckAllFields() {
        if (name.length() == 0) {
            name.setError("Name is required");
            return false;
        }

        if (description.length() == 0) {
            description.setError("Description is required");
            return false;
        }

        if (id.length() == 0) {
            id.setError("Id is required");
            return false;
        }

        if (price.length() == 0) {
            price.setError("Price is required");
            return false;
        }
        if (purl.length() == 0) {
            purl.setError("Url is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
}