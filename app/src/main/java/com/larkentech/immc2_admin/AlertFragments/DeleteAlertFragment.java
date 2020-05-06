package com.larkentech.immc2_admin.AlertFragments;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.larkentech.immc2_admin.R;

import es.dmoral.toasty.Toasty;

import static es.dmoral.toasty.Toasty.LENGTH_LONG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteAlertFragment extends DialogFragment {

    String bookName;
    String bookDesigner;
    String bookPrice;
    String bookImage;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView bookname,bookprice,bookdesigner;
    Button cancel,delete;
    ImageView bookimage;

    public String bookID;
    public String bookCategoryID;
    public String bookSubCategoryID;


    public DeleteAlertFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        bookName = getArguments().getString("BookName");
        bookDesigner = getArguments().getString("BookDesigner");
        bookPrice = getArguments().getString("BookPrice160Pages");
        bookImage = getArguments().getString("BookImage");

        bookID = getArguments().getString("BookID");
        bookCategoryID = getArguments().getString("BookCategory");
        bookSubCategoryID = getArguments().getString("BookSubCategory");

        return inflater.inflate(R.layout.fragment_delete_alert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();

        bookname = (TextView) view.findViewById(R.id.bookName);
        bookdesigner = (TextView) view.findViewById(R.id.bookDesigner);
        bookprice = (TextView) view.findViewById(R.id.bookPriceDelete);
        bookimage = (ImageView) view.findViewById(R.id.itemImage);
        cancel = view.findViewById(R.id.cancel);
        delete =view.findViewById(R.id.delete);

        bookname.setText(bookName);
        bookdesigner.setText("Designed By "+bookDesigner);
        bookprice.setText("Rs."+bookPrice);
        Glide
                .with(getContext())
                .load(bookImage)
                .centerCrop()
                .into(bookimage);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook(bookID);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    }

    private void deleteBook(String bookID){
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = firebaseDatabase.getReference().child("BooksListNames").child(bookID);
        databaseReference2.removeValue();
        if (bookSubCategoryID != null)
        {
            DatabaseReference databaseReference = firebaseDatabase.getReference().child("BookDetails").child(bookCategoryID).child(bookSubCategoryID).child(bookID);
            databaseReference.removeValue();
            getDialog().dismiss();
            Toasty.success(getContext(),"Book Successfully Deleted").show();
        }
        else {

            DatabaseReference databaseReference1 = firebaseDatabase.getReference().child("BookDetails").child(bookCategoryID).child(bookID);
            databaseReference1.removeValue();
            getDialog().dismiss();
            Toasty.success(getContext(),"Book Successfully Deleted").show();

        }

    }
}
