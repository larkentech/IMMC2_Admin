package com.larkentech.immc2_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginId;
    EditText password;
    Button loginBtn;
    String adminLoginId;
    String adminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginId = findViewById(R.id.loginId);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  adminLoginId = loginId.getText().toString().trim();
                  adminPassword = password.getText().toString().trim();

                if(adminLoginId.length() == 0 || adminPassword.length() ==0 )
                {
                    Toast.makeText(getApplicationContext(),"Please Try Again",Toast.LENGTH_LONG).show();
                }else{
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference().child("AdminCredentials");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userName = dataSnapshot.child("loginID").getValue(String.class);
                            if(adminLoginId.compareToIgnoreCase(userName) != 0)
                            {
                                Toast.makeText(getApplicationContext(),"Invalid User ID",Toast.LENGTH_LONG).show();
                            }else{
                                String pwd1 = dataSnapshot.child("password").getValue(String.class);
                                if (adminPassword.compareToIgnoreCase(pwd1) != 0){

                                    Toast.makeText(getApplicationContext(),"Invalid Password. Try Again",Toast.LENGTH_LONG).show();
                                }else{
                                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
