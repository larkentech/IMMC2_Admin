package com.larkentech.immc2_admin;


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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
        bookPrice = getArguments().getString("BookPrice");
        bookImage = getArguments().getString("BookImage");
        return inflater.inflate(R.layout.fragment_delete_alert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookname = (TextView) view.findViewById(R.id.bookName);
        bookdesigner = (TextView) view.findViewById(R.id.bookDesigner);
        bookprice = (TextView) view.findViewById(R.id.bookPrice);
        bookimage = (ImageView) view.findViewById(R.id.itemImage);
        cancel = view.findViewById(R.id.cancel);
        delete =view.findViewById(R.id.delete);

        bookname.setText(bookName);
        bookdesigner.setText(bookDesigner);
        bookprice.setText(bookPrice);
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

            }
        });

    }




}
