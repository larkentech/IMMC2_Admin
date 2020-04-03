package com.larkentech.immc2_admin.AlertFragments;


import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
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
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.larkentech.immc2_admin.MainActivity;
import com.larkentech.immc2_admin.ModalClasses.BookModal;
import com.larkentech.immc2_admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    ArrayList<String> bookImages;
    String bookDesc;
    String image1;
    String image2;
    String image3;
    String image4;
    String image5;
    String image6;
    String image7;

    String pointer;


    CardView addPhotoCard7;
    CardView addPhotoCard6;
    CardView addPhotoCard5;

    int flag;

    List<String> imageuri = new ArrayList<>(7);
    List<String> imageuri2 = new ArrayList<>();


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
    private BookModal bookModel;

    Button updateText;
    DatabaseReference databaseReference1;

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

        getDialog().setCanceledOnTouchOutside(true);
        // Inflate the layout for this fragment

        bookName = getArguments().getString("BookName");
        bookDesigner = getArguments().getString("BookDesigner");
        bookPrice160Pages = getArguments().getString("BookPrice160Pages");
        bookPrice200Pages = getArguments().getString("BookPrice200Pages");
        bookPrice240Pages = getArguments().getString("BookPrice240Pages");
        bookDesc = getArguments().getString("BookDesc");
        bookImage = getArguments().getString("BookImage");
        image1 = getArguments().getString("Image1");
        image2 = getArguments().getString("Image2");
        image3 = getArguments().getString("Image3");
        image4 = getArguments().getString("Image4");

        imageuri.add("Hello");
        imageuri.add("Hello");
        imageuri.add("Hello");
        imageuri.add("Hello");

        if (getArguments().containsKey("Image5") && getArguments().containsKey("Image6") &&
                getArguments().containsKey("Image7")) {
            image5 = getArguments().getString("Image2");
            image6 = getArguments().getString("Image6");
            image7 = getArguments().getString("Image7");
            imageuri.add("Hello");
            imageuri.add("Hello");
            imageuri.add("Hello");
        }

        bookID = getArguments().getString("BookID");
        bookCategoryID = getArguments().getString("BookCategory");
        bookSubCategoryID = getArguments().getString("BookSubCategory");


        return inflater.inflate(R.layout.fragment_edit_alert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
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


            bookImage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 1;
                    pointer = "bookImage1";
                }
            });

            bookImage2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 2;
                    pointer = "bookImage2";
                }
            });

            bookImage3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 3;
                    pointer = "bookImage3";
                }
            });


            bookImage4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 4;
                    pointer = "bookImage4";
                }
            });
            bookImage5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 5;
                    pointer = "bookImage5";
                }
            });

            bookImage6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 6;
                    pointer = "bookImage6";
                }
            });

            bookImage7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookImage();
                    flag = 7;
                    pointer = "bookImage7";
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

            FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
            databaseReference1 = firebaseDatabase1.getReference().child("BookDetails")
                    .child(bookCategoryID).child(bookSubCategoryID)
                    .child(bookID);


            Log.v("TAG","BookCAtegory"+bookCategoryID);
            Log.v("TAG","BookCAtegory"+bookSubCategoryID);
            Log.v("TAG","BookCAtegory"+bookID);
            databaseReference1.child("BookImages").addListenerForSingleValueEvent(new ValueEventListener() {
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








            updateText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String bookNameStr = bookname.getText().toString();
                    String bookPrice160PagesStr = bookprice160Pages.getText().toString();
                    String bookPrice200PagesStr = bookprice200Pages.getText().toString();
                    String bookPrice240PagesStr = bookprice240Pages.getText().toString();
                    String bookDescStr = bookdesc.getText().toString();
                    String bookDesignerStr = bookdesigner.getText().toString();
                    String bookCategoryStr = bookcategory.getText().toString();
                    String bookSubCategoryStr = booksubcategory.getText().toString();

                    editBookMap.put("BookName", bookname.getText().toString());
                    editBookMap.put("BookPrice160Pages", bookprice160Pages.getText().toString());
                    editBookMap.put("BookPrice200Pages", bookprice200Pages.getText().toString());
                    editBookMap.put("BookPrice240Pages", bookprice240Pages.getText().toString());
                    editBookMap.put("BookDesc", bookdesc.getText().toString());
                    editBookMap.put("BookCategory", bookcategory.getText().toString());
                    editBookMap.put("BookSubCategory", booksubcategory.getText().toString());
                    editBookMap.put("BookDesigner", bookdesigner.getText().toString());
                    editBookMap.put("BookID", bookID);

                    FirebaseDatabase firebaseDatabase10 = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference10 = firebaseDatabase10.getReference();
                    databaseReference10.child("BookDetails").child(bookCategoryID).child(bookSubCategoryID).child(bookID).updateChildren(editBookMap);

                    Toasty.success(getContext(),"Update Successfull").show();
                    dismiss();
                    Intent x = new Intent(getContext(), MainActivity.class);
                    startActivity(x);
                    getActivity().finish();
                }
            });


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUpdateBook(bookID);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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


        switch (flag) {
            case 1:
                bookImage1.setImageURI(data.getData());
                imageuri.set(0, data.getData().toString());
                break;
            case 2:
                bookImage2.setImageURI(data.getData());
                imageuri.set(1, data.getData().toString());
                break;
            case 3:
                bookImage3.setImageURI(data.getData());
                imageuri.set(2, data.getData().toString());
                break;
            case 4:
                bookImage4.setImageURI(data.getData());
                imageuri.set(3, data.getData().toString());
                break;
            case 5:
                bookImage5.setImageURI(data.getData());
                imageuri.set(4, data.getData().toString());
                break;
            case 6:
                bookImage6.setImageURI(data.getData());
                imageuri.set(5, data.getData().toString());
                break;
            case 7:
                bookImage7.setImageURI(data.getData());
                imageuri.set(6, data.getData().toString());
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
            editBookMap.put("BookID", bookID);
            for (int i = 0; i < imageuri.size(); i++) {
                if (imageuri.get(i).matches("Hello")) {
                    continue;
                }
                Log.v("TAG", "ImageUri:" + imageuri.get(i));
                uploadImage(Uri.parse(imageuri.get(i)), i, imageuri.size());
                if (i == imageuri.size()-1)
                {
                    dismiss();
                    Intent x = new Intent(getContext(),MainActivity.class);
                    startActivity(x);
                    getActivity().finish();
                }


            }

        }

    }

    public void uploadImage(Uri uri, final int count, final int imagesCount) {
        final int count11 = count + 1;

        final int max = 10;
        final int min = 1;
        final int random = new Random().nextInt((max-min) + 1) + min;

        final StorageReference reference = mStorageRef.child(random+ "." + getExtension(uri));
        reference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                addImagesMap.put("Image" + count11, uri.toString());
                                //Toasty.success(getContext(), "Book Image Editted Successfully").show();
                                FirebaseDatabase firebaseDatabase11 = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference11 = firebaseDatabase11.getReference();
                                DatabaseReference databaseReference12 = firebaseDatabase11.getReference();
                                //databaseReference12.child("BookDetails").child(bookCategoryID).child(bookSubCategoryID).child(bookID).updateChildren(editBookMap);
                                databaseReference11.child("BookDetails").child(bookCategoryID).child(bookSubCategoryID).child(bookID).child("BookImages").updateChildren(addImagesMap);
                                //Toasty.success(getContext(), "Book Editted Successfully").show();
                                if (count == 0)
                                {
                                    databaseReference1.child("BookImage").setValue(uri.toString());

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
