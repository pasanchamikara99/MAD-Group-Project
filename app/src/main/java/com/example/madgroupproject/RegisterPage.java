package com.example.madgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    Button registerbtn;
    EditText username,email,phoneNo,password,confirmPassword;
    ProgressBar progressBar;
    User userObject = new User();
    DatabaseReference dbRef,dbSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        registerbtn = findViewById(R.id.registerBtn);
        username = findViewById(R.id.editTxtUserName);
        email = findViewById(R.id.editTxtUserEmail);
        phoneNo = findViewById(R.id.editTxtUserPhoneNo);
        password = findViewById(R.id.editTxtUserPassword);
        confirmPassword = findViewById(R.id.editTxtUserCpassword);



        progressBar = findViewById(R.id.regProgressBar);



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }
        });

    }

    public void HasAccount(View view){
        startActivity(new Intent(RegisterPage.this,LoginPage.class));
    }
    
    

    private void validateUser() {

        String userName = username.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPhoneNo = phoneNo.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String userConfirmPassword = confirmPassword.getText().toString().trim();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");

        try {

            if (userName.isEmpty()) {
                //Toast.makeText(this,"User name is  empty",Toast.LENGTH_SHORT).show();
                username.setError("User name is Required !!!! ");
                username.requestFocus();
                return;
            }

            if (userEmail.isEmpty()) {
                email.setError("Email  is Required !!!! ");
                email.requestFocus();
                return;
            }

            if (userPhoneNo.isEmpty()) {
                phoneNo.setError("Phone Number  is Required !!!! ");
                phoneNo.requestFocus();
                return;
            }

            if (userPassword.isEmpty()) {
                password.setError("Password is Required !!!! ");
                password.requestFocus();
                return;
            }

            if (userConfirmPassword.isEmpty()) {
                confirmPassword.setError("Confirm Password is Required !!!! ");
                confirmPassword.requestFocus();
                return;
            }

            if (userPhoneNo.length() > 10 || userPhoneNo.length() < 10) {
                phoneNo.setError("Invalid Phone Number !!!! ");
                phoneNo.requestFocus();
                return;
            }

            if (userPassword.length() < 8) {
                confirmPassword.setError("password must contain 8 characters !!!! ");
                confirmPassword.requestFocus();
                return;
            }

            if (!userPassword.equals(userConfirmPassword)) {
                confirmPassword.setError("password Mismatch , Try again !!!! ");
                confirmPassword.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                email.setError("Invalid Email Address !!!! ");
                email.requestFocus();
                return;
            }


            progressBar.setVisibility(View.VISIBLE);


            userObject.setUsername(userName);
            userObject.setPhoneNo(userPhoneNo);
            userObject.setEmail(userEmail);
            userObject.setPassword(userPassword);


            dbSearch = FirebaseDatabase.getInstance().getReference().child("Users").child(userName);
            dbSearch.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()) {
                        username.setError("user name already in use");
                        progressBar.setVisibility(View.GONE);
                        username.requestFocus();
                    } else {
                        dbRef.child(userName).setValue(userObject);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), " User Registered Successfully ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterPage.this,LoginPage.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch(NumberFormatException e){
           // Toast.makeText(getApplicationContext(),"Invalid Contact Number ",Toast.LENGTH_SHORT);
        }


        //Toast.makeText(this,"User Register Complete ",Toast.LENGTH_SHORT).show();


    }
}