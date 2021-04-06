package com.hcl.demoapp_firebasegoogle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText email,password;
    Button rgstrbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email_user);
        password = findViewById(R.id.password_user);
        rgstrbtn = findViewById(R.id.regsiter_buttons);
        mAuth = FirebaseAuth.getInstance();

        rgstrbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String get_email = email.getText().toString();
                String get_password = password.getText().toString();

                if(TextUtils.isEmpty(get_email) || TextUtils.isEmpty(get_password)){

                    Toast.makeText(RegisterActivity.this,"Please enter all the required fields",Toast.LENGTH_LONG).show();

                } if(get_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                else{

                        registerUser(get_email,get_password);
                }
            }
        });
    }


    public void registerUser(String eml, String pass){

        mAuth.createUserWithEmailAndPassword(eml,pass).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registration Successfully Done",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,MainPageActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Registration Failed...Please try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}