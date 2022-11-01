package com.example.madgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    TextView SignUp;
    Button loginBtn;
    EditText userName,password;
    ProgressBar loginprogress;

    DatabaseReference dbSearch;

    protected void onStart(){
        super.onStart();

        sessionManagement session = new sessionManagement(LoginPage.this);

        String isloggedIn = session.getSession();


        if(!isloggedIn.equals("null")){

            if(isloggedIn.equals("admin")){
                startActivity(new Intent(LoginPage.this,AdminPage.class));
                finish();
            }else{
                startActivity(new Intent(LoginPage.this,MenuPage.class));
                finish();
            }

        }else{

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SignUp = findViewById(R.id.noAccountLink);
        loginBtn = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.loginTxtUserName);
        password = findViewById(R.id.editTxtPassword);
        loginprogress = findViewById(R.id.loginProgressBar);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }


    //if user click Sign In textView
    public void noAccount(View view){
        startActivity(new Intent(LoginPage.this,RegisterPage.class));
    }


    // if user click login button
    private void loginUser(){

        //get user inputs
        String username = userName.getText().toString().trim();
        String pass = password.getText().toString().trim();


        //set progress bar visible
        loginprogress.setVisibility(View.VISIBLE);

        if(username.isEmpty()){
            loginprogress.setVisibility(View.GONE);
            userName.setError("User name is Required !!!! ");
            userName.requestFocus();
            return;
        }
        else if(pass.isEmpty()){
            loginprogress.setVisibility(View.GONE);
            password.setError("Password is Required !!!! ");
            password.requestFocus();
            return;
        }
        else {

            //search user entered user name in the database
            dbSearch = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
            dbSearch.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //if user name not avaliable in database
                    if (!snapshot.hasChildren()) {
                        userName.setError("Invalid User Name ");
                        loginprogress.setVisibility(View.GONE);
                        userName.requestFocus();
                    } else {

                        //get database values to strings
                        String newPass = snapshot.child("password").getValue().toString();
                        String newUserName = snapshot.child("username").getValue().toString();


                        //if user entered as admin
                        if (username.equals("admin") && pass.equals(newPass)) {
                            loginprogress.setVisibility(View.GONE);

                            //redireted to admin page
                            startActivity(new Intent(LoginPage.this, AdminPage.class));
                            finish();
                        }

                        //check user entered values are matching
                        else if (username.equals(newUserName) && pass.equals(newPass)) {

                            loginprogress.setVisibility(View.GONE);

                            User user = new User(username);

                            sessionManagement session = new sessionManagement(LoginPage.this);
                            session.saveSession(user);


                            //redireted to menu page
                            startActivity(new Intent(LoginPage.this, Food_menu.class));
                            finish();
                        }

                        //user name or password not match
                        else {

                            loginprogress.setVisibility(View.GONE);

                            //view error messages
                            userName.setError("Invalid User Name or Password !!!! ");
                            userName.requestFocus();

                            password.setError("Invalid User Name or Password !!!! ");
                            password.requestFocus();
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}