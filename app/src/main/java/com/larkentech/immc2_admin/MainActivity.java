package com.larkentech.immc2_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView admiLoginId;
    CardView addBook;
    CardView editBook;
    CardView deleteBook;
    CardView orders;
    Button signout;
    CardView feedback;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBook = (CardView) findViewById(R.id.addBook);
        editBook = (CardView) findViewById(R.id.editBook);
        deleteBook = (CardView) findViewById(R.id.deleteBook);
        orders = (CardView) findViewById(R.id.orders);
        feedback = (CardView) findViewById(R.id.feedback);

        admiLoginId = findViewById(R.id.adminLoginId);

        signout = (Button) findViewById(R.id.signOut);



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("AdminCredentials");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                admiLoginId.setText(dataSnapshot.child("loginID").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBook();
            }
        });

        editBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditBook();
            }
        });

        deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteBook();
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrders();
            }
        });



        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedback();
            }
        });



    }




    public void openAddBook(){
        Intent intent = new Intent(getApplicationContext(),DetailsContainerActivity.class);
        intent.putExtra("Category","AddBook");
        startActivity(intent);
    }

    private void openEditBook() {
        Intent intent = new Intent(getApplicationContext(),DetailsContainerActivity.class);
        intent.putExtra("Category","EditBook");
        startActivity(intent);
    }

    public void openDeleteBook(){
        Intent intent = new Intent(getApplicationContext(),DetailsContainerActivity.class);
        intent.putExtra("Category","DeleteBook");
        startActivity(intent);
    }

    private void openOrders() {
        Intent intent = new Intent(getApplicationContext(),DetailsContainerActivity.class);
        intent.putExtra("Category","Orders");
        startActivity(intent);
    }

    private void openFeedback() { Intent intent = new Intent(getApplicationContext(),DetailsContainerActivity.class);
        intent.putExtra("Category","Feedback");
        startActivity(intent);
    }

}
