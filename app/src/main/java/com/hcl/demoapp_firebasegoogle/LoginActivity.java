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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email_lg,password_lg;
    Button lg_btns;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_lg = findViewById(R.id.email_login);
        password_lg =findViewById(R.id.password_login);
        lg_btns = findViewById(R.id.login_buttons);

        mAuth = FirebaseAuth.getInstance();


        lg_btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eml_lg = email_lg.getText().toString();
                String pass_lg = password_lg.getText().toString();

                if(TextUtils.isEmpty(eml_lg) || TextUtils.isEmpty(pass_lg)){

                    Toast.makeText(LoginActivity.this,"Please enter all the required fields",Toast.LENGTH_LONG).show();

                }else {

                    loginUser(eml_lg, pass_lg);

                }

            }
        });
    }

    private void loginUser(String eml_lg, String pass_lg) {

        mAuth.signInWithEmailAndPassword(eml_lg,pass_lg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,MainPageActivity.class);
                            startActivity(intent);

                        }

                        else{
                            Toast.makeText(LoginActivity.this,"Error Occured:" + task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }

            }
        });
    }
}