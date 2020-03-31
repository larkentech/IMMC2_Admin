package com.larkentech.immc2_admin.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.larkentech.immc2_admin.BookModal;
import com.larkentech.immc2_admin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditAlertFragmentRevised extends DialogFragment {


    //Views
    TextView bookname;
    TextView bookdesc;
    TextView bookprice160Pages;
    TextView bookprice200Pages;
    TextView bookprice240Pages;
    TextView bookdesigner;
    TextView bookcategory;
    TextView booksubcategory;

    ImageView bookImage1;
    ImageView bookImage2;
    ImageView bookImage3;
    ImageView bookImage4;
    ImageView bookImage5;
    ImageView bookImage6;
    ImageView bookImage7;

    Button update;
    Button updateText;

    //Variables
    String bookName;
    String bookDesigner;
    String bookPrice160Pages;
    String bookPrice200Pages;
    String bookPrice240Pages;
    String bookImage;
    ArrayList<String> bookImages;
    String bookDesc;
    String image1;
    String image2;
    String image3;
    String image4;
    String image5;
    String image6;
    String image7;

    //Arguments
    public String bookID;
    public String bookCategoryID;
    public String bookSubCategoryID;

    //Database Reference for recieveing
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    //Cards To Hide
    CardView addPhotoCard7;
    CardView addPhotoCard6;
    CardView addPhotoCard5;

    //Misc
    int flag;
    ProgressDialog progressDialog;
    List<String> imagesUrl;

    public EditAlertFragmentRevised() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_alert_revised, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //View References

        bookID = getArguments().getString("BookId");
        bookCategoryID = getArguments().getString("Category");
        bookSubCategoryID = getArguments().getString("Subcategory");

        bookname = (TextView) view.findViewById(R.id.bookName);
        bookdesigner = (TextView) view.findViewById(R.id.designerName);
        bookprice160Pages = (TextView) view.findViewById(R.id.price1);
        bookprice200Pages = (TextView) view.findViewById(R.id.price2);
        bookprice240Pages = (TextView) view.findViewById(R.id.price3);
        bookdesc = (TextView) view.findViewById(R.id.description);
        bookcategory = (TextView) view.findViewById(R.id.categorySpinner);
        booksubcategory = (TextView) view.findViewById(R.id.subCategorySpinner);
        bookImage1 = (ImageView) view.findViewById(R.id.bookImage1);
        bookImage2 = (ImageView) view.findViewById(R.id.bookImage2);
        bookImage3 = (ImageView) view.findViewById(R.id.bookImage3);
        bookImage4 = (ImageView) view.findViewById(R.id.bookImage4);
        bookImage5 = (ImageView) view.findViewById(R.id.bookImage5);
        bookImage6 = (ImageView) view.findViewById(R.id.bookImage6);
        bookImage7 = (ImageView) view.findViewById(R.id.bookImage7);
        addPhotoCard5 = (CardView) view.findViewById(R.id.addPhotoCard5);
        addPhotoCard6 = (CardView) view.findViewById(R.id.addPhotoCard6);
        addPhotoCard7 = (CardView) view.findViewById(R.id.addPhotoCard7);
        update = (Button) view.findViewById(R.id.update);
        updateText = view.findViewById(R.id.updatePhotos);

        if (bookCategoryID.matches("Engineering")) {
            addPhotoCard5.setVisibility(View.VISIBLE);
            addPhotoCard6.setVisibility(View.VISIBLE);
            addPhotoCard7.setVisibility(View.VISIBLE);
        } else {
            addPhotoCard5.setVisibility(View.GONE);
            addPhotoCard6.setVisibility(View.GONE);
            addPhotoCard7.setVisibility(View.GONE);
        }

        //To get data from Firebase
        getDataFromFirebase();


        bookImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 1;
            }
        });

        bookImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 2;
            }
        });

        bookImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 3;
            }
        });


        bookImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 4;
            }
        });
        bookImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 5;
            }
        });

        bookImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 6;
            }
        });

        bookImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openBookImage();
                flag = 7;
            }
        });



    }

    private void getDataFromFirebase() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("BookDetails")
                .child(bookCategoryID).child(bookSubCategoryID).child(bookID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookModal modal = dataSnapshot.getValue(BookModal.class);
                bookName = modal.getBookName();
                bookDesigner = modal.getBookDesigner();
                bookPrice160Pages = modal.getBookPrice160Pages();
                bookPrice200Pages = modal.getBookPrice200Pages();
                bookPrice240Pages = modal.getBookPrice240Pages();
                bookDesc = modal.getBookDesc();
                //To Get images from database and set it cardviews
                getImagesFromFirebase();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImagesFromFirebase() {
        imagesUrl = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("BookDetails")
                .child(bookCategoryID).child(bookSubCategoryID).child(bookID);
        databaseReference.child("BookImages").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookModal booksmodal = dataSnapshot.getValue(BookModal.class);
                Log.v("TAG","IAmge1:"+booksmodal.getImage1());
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage1())
                        .centerCrop()
                        .into(bookImage1);
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage2())
                        .centerCrop()
                        .into(bookImage2);
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage3())
                        .centerCrop()
                        .into(bookImage3);
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage4())
                        .centerCrop()
                        .into(bookImage4);
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage5())
                        .centerCrop()
                        .into(bookImage5);
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage6())
                        .centerCrop()
                        .into(bookImage6);
                Glide
                        .with(getContext())
                        .load(booksmodal.getImage7())
                        .centerCrop()
                        .into(bookImage7);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setImagesToCards(List<String> imagesUrl) {
        Glide
                .with(getContext())
                .load(imagesUrl.get(0))
                .centerCrop()
                .into(bookImage1);
        Glide
                .with(getContext())
                .load(imagesUrl.get(1))
                .centerCrop()
                .into(bookImage2);
        Glide
                .with(getContext())
                .load(imagesUrl.get(2))
                .centerCrop()
                .into(bookImage3);
        Glide
                .with(getContext())
                .load(imagesUrl.get(3))
                .centerCrop()
                .into(bookImage4);

        if (bookCategoryID.matches("Engineering"))
        {
            Glide
                    .with(getContext())
                    .load(imagesUrl.get(4))
                    .centerCrop()
                    .into(bookImage5);
            Glide
                    .with(getContext())
                    .load(imagesUrl.get(5))
                    .centerCrop()
                    .into(bookImage6);
            Glide
                    .with(getContext())
                    .load(imagesUrl.get(6))
                    .centerCrop()
                    .into(bookImage7);
        }



        progressDialog.dismiss();
    }
}
