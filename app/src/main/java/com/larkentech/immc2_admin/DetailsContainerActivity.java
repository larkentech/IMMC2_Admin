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

            case "EditBook":
                FragmentManager manager1 = getSupportFragmentManager();
                EditBookFragment editBookFragment = new EditBookFragment();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.detailsActivityContainer,editBookFragment);
                transaction1.commit();
                break;
            case "DeleteBook":
                FragmentManager manager2 = getSupportFragmentManager();
                DeleteBookFragment deleteBookFragment = new DeleteBookFragment();
                FragmentTransaction transaction2 = manager2.beginTransaction();
                transaction2.replace(R.id.detailsActivityContainer,deleteBookFragment);
                transaction2.commit();
                break;

            case "Orders":
                FragmentManager manager3 = getSupportFragmentManager();
                OrdersFragment ordersFragment = new OrdersFragment();
                FragmentTransaction transaction3 = manager3.beginTransaction();
                transaction3.replace(R.id.detailsActivityContainer,ordersFragment);
                transaction3.commit();
                break;
            case "Feedback":
                FragmentManager manager4 = getSupportFragmentManager();
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                FragmentTransaction transaction4 = manager4.beginTransaction();
                transaction4.replace(R.id.detailsActivityContainer,feedbackFragment);
                transaction4.commit();
                break;

            case "AddCategory":
                FragmentManager manager5 = getSupportFragmentManager();
                AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
                FragmentTransaction transaction5 = manager5.beginTransaction();
                transaction5.replace(R.id.detailsActivityContainer,addCategoryFragment);
                transaction5.commit();
                break;

        }
    }
}
