package com.larkentech.immc2_admin;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditAlertFragment extends DialogFragment {

    String bookName;
    String bookDesigner;
    String bookPrice;
    String bookImage;
    String bookDesc;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView bookname;
    TextView bookdesc;
    TextView bookprice;
    TextView bookdesigner;
    TextView bookcategory;
    TextView booksubcategory;

    Button update;
    ImageView bookimage;

    public String bookID;
    public String bookCategoryID;
    public String bookSubCategoryID;


    public EditAlertFragment() {
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
        bookDesc = getArguments().getString("BookDesc");
        bookImage = getArguments().getString("BookImage");

        bookID = getArguments().getString("BookID");
        bookCategoryID = getArguments().getString("BookCategory");
        bookSubCategoryID = getArguments().getString("BookSubCategory");

        return inflater.inflate(R.layout.fragment_edit_alert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookname = (TextView) view.findViewById(R.id.bookName);
        bookdesigner = (TextView) view.findViewById(R.id.designerName);
        bookprice = (TextView) view.findViewById(R.id.bookPrice);
        bookdesc = (TextView) view.findViewById(R.id.bookDesc);
        bookcategory = (TextView) view.findViewById(R.id.categorySpinner);
        booksubcategory = (TextView) view.findViewById(R.id.subCategorySpinner);
        bookimage = (ImageView) view.findViewById(R.id.bookImage1);

        update = view.findViewById(R.id.update);


        bookname.setText(bookName);
        bookdesigner.setText(bookDesigner);
        bookdesc.setText(bookDesc);
        bookcategory.setText(bookCategoryID);
        booksubcategory.setText(bookSubCategoryID);
        bookprice.setText(bookPrice);
        Glide
                .with(getContext())
                .load(bookImage)
                .centerCrop()
                .into(bookimage);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    }
}
