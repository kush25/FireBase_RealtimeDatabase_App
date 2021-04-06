package com.hcl.demoapp_firebasegoogle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainPageActivity extends AppCompatActivity {

    Button lgout,add;
    FirebaseAuth mAuth;
    EditText myname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        add = findViewById(R.id.add_user);
        myname = findViewById(R.id.user_name);

        lgout = findViewById(R.id.logout_buttons);

        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                startActivity(new Intent(MainPageActivity.this,MainActivity.class));

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String my_name = myname.getText().toString();

                if(my_name.isEmpty()){
                    Toast.makeText(MainPageActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference().child("KushDatabase").push().child("Name").setValue(my_name);
                }
            }
        });







      //  Single line adding
      // FirebaseDatabase.getInstance().getReference().child("KushDatabase").child("AndroidApp").setValue("KG");

        //Mulitple elements
//        HashMap<String,Object> hmap = new HashMap<>();
//        hmap.put("Name","Kush");
//        hmap.put("Email","kush@google.com");
//        hmap.put("Contact Number","7147889999");
//
//        FirebaseDatabase.getInstance().getReference().child("KushDatabase").updateChildren(hmap);

    }

}