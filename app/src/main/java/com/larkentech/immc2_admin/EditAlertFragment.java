package com.larkentech.immc2_admin;


import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

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
    String bookPrice160Pages;
    String bookPrice200Pages;
    String bookPrice240Pages;
    String bookImage;
    String bookDesc;

    CardView addPhotoCard7;
    CardView addPhotoCard6;
    CardView addPhotoCard5;

    int flag;

    List<Uri> imageuri = new ArrayList<>();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

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

    HashMap<String, Object> editBookMap = new HashMap<>();
    HashMap<String, Object> addImagesMap = new HashMap<>();

    Button update;
    ImageView bookimage;

    StorageReference mStorageRef;

    public static final int IMAGE_CODE = 1;

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
        bookPrice160Pages = getArguments().getString("BookPrice160Pages");
        bookPrice200Pages = getArguments().getString("BookPrice200Pages");
        bookPrice240Pages = getArguments().getString("BookPrice240Pages");
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

        mStorageRef = FirebaseStorage.getInstance().getReference("NewBooks");

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


        bookImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=1;
            }
        });

        bookImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=2;
            }
        });

        bookImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=3;
            }
        });


        bookImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=4;
            }
        });
        bookImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=5;
            }
        });

        bookImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=6;
            }
        });

        bookImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag=7;
            }
        });


        bookname.setText(bookName);
        bookdesigner.setText(bookDesigner);
        bookdesc.setText(bookDesc);
        bookcategory.setText(bookCategoryID);
        booksubcategory.setText(bookSubCategoryID);
        bookprice160Pages.setText(bookPrice160Pages);
        bookprice200Pages.setText(bookPrice200Pages);
        bookprice240Pages.setText(bookPrice240Pages);

        Glide
                .with(getContext())
                .load(bookImage)
                .centerCrop()
                .into(bookImage1);

     
        if (bookcategory.equals("Engineering") )
        {
            addPhotoCard5.setVisibility(View.VISIBLE);
            addPhotoCard6.setVisibility(View.VISIBLE);
            addPhotoCard7.setVisibility(View.VISIBLE);
        }
        else{
            addPhotoCard5.setVisibility(View.GONE);
            addPhotoCard6.setVisibility(View.GONE);
            addPhotoCard7.setVisibility(View.GONE);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateBook(bookID);
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

    public void openBookImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE && requestCode == RESULT_OK && data != null && data.getData() != null)
            ;

        imageuri.add(data.getData());

        switch (flag) {
            case 1:
                bookImage1.setImageURI(imageuri.get(0));
                break;
            case 2:
                bookImage2.setImageURI(imageuri.get(1));
                break;
            case 3:
                bookImage3.setImageURI(imageuri.get(2));
                break;
            case 4:
                bookImage4.setImageURI(imageuri.get(3));
                break;
            case 5:
                bookImage5.setImageURI(imageuri.get(4));
                break;
            case 6:
                bookImage6.setImageURI(imageuri.get(5));
                break;
            case 7:
                bookImage7.setImageURI(imageuri.get(6));
                break;
        }
    }

    private String getExtension(Uri uri) {

        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void openUpdateBook(String bookID) {
        String bookNameStr = bookname.getText().toString();
        String bookPrice160PagesStr = bookprice160Pages.getText().toString();
        String bookPrice200PagesStr = bookprice200Pages.getText().toString();
        String bookPrice240PagesStr = bookprice240Pages.getText().toString();
        String bookDescStr = bookdesc.getText().toString();
        String bookDesignerStr = bookdesigner.getText().toString();
        String bookCategoryStr = bookcategory.getText().toString();
        String bookSubCategoryStr = booksubcategory.getText().toString();

        if (bookNameStr.isEmpty() || bookPrice200PagesStr.isEmpty() || bookPrice240PagesStr.isEmpty() || bookPrice160PagesStr.isEmpty() || bookDescStr.isEmpty() || bookDesignerStr.isEmpty() || bookCategoryStr.isEmpty() || bookSubCategoryStr.isEmpty()) {
            Toasty.error(getContext(), "Enter Required Details").show();
        } else {


            editBookMap.put("BookName", bookname.getText().toString());
            editBookMap.put("BookPrice160Pages", bookprice160Pages.getText().toString());
            editBookMap.put("BookPrice200Pages", bookprice200Pages.getText().toString());
            editBookMap.put("BookPrice240Pages", bookprice240Pages.getText().toString());
            editBookMap.put("BookDesc", bookdesc.getText().toString());
            editBookMap.put("BookCategory", bookcategory.getText().toString());
            editBookMap.put("BookSubCategory", booksubcategory.getText().toString());
            editBookMap.put("BookDesigner", bookdesigner.getText().toString());
            editBookMap.put("BookId", bookID);
            for (int i = 0; i < imageuri.size(); i++) {
                uploadImage(imageuri.get(i), i, imageuri.size());
            }

        }

    }

    public void uploadImage(Uri uri, int count, final int imagesCount) {
        final int count11 = count + 1;


        final StorageReference reference = mStorageRef.child("image" + Integer.toString(count) + "." + getExtension(imageuri.get(count)));
        reference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                addImagesMap.put("Image" + count11, uri.toString());
                                editBookMap.put("BookImage", addImagesMap.get("Image1"));
                                Toasty.success(getContext(), "Book Image Editted Successfully").show();
                                if (count11 == imagesCount) {
                                    FirebaseDatabase firebaseDatabase11 = FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference11 = firebaseDatabase11.getReference();
                                    DatabaseReference databaseReference12 = firebaseDatabase11.getReference();
                                    databaseReference12.child("BookDetails").child(bookCategoryID).child(bookSubCategoryID).child(bookID).setValue(editBookMap);
                                    databaseReference11.child("BookDetails").child(bookCategoryID).child(bookSubCategoryID).child(bookID).child("BookImages").setValue(addImagesMap);
                                    Toasty.success(getContext(), "Book Editted Successfully").show();
                                }

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...

                    }
                });


    }
}
