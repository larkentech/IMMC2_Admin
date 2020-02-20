package com.larkentech.immc2_admin;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DetailsContainerActivity extends AppCompatActivity {

    FrameLayout detailsActivityContainer;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_container);
        category = getIntent().getExtras().getString("Category");

        detailsActivityContainer = findViewById(R.id.detailsActivityContainer);


        switch (category)
        {
            case "AddBook":
                FragmentManager manager = getSupportFragmentManager();
                AddBookFragment addBookFragment = new AddBookFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.detailsActivityContainer,addBookFragment);
                transaction.commit();
                break;
        }

    }
}
