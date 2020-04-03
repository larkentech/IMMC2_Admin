package com.larkentech.immc2_admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import com.larkentech.immc2_admin.ModalClasses.BookModal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;

import static es.dmoral.toasty.Toasty.*;

public class BookDetailsEditActivity extends AppCompatActivity {

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

    Button uploadPhotos;
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

    HashMap<String, Object> editBookMap = new HashMap<>();
    HashMap<String, Object> addImagesMap = new HashMap<>();
    public static final int IMAGE_CODE = 1;
    List<String> imageuri = new ArrayList<>();
    int totalImages = 4;

    StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_edit);

        mStorageRef = FirebaseStorage.getInstance().getReference("BookImages");
        progressDialog = new ProgressDialog(this);

        //Initial Setting of imageUri
        imageuri.add("Hello");
        imageuri.add("Hello");
        imageuri.add("Hello");
        imageuri.add("Hello");


        bookID = getIntent().getExtras().getString("BookId");
        bookCategoryID = getIntent().getExtras().getString("Category");
        bookSubCategoryID = getIntent().getExtras().getString("Subcategory");

        bookname = (TextView) findViewById(R.id.bookName);
        bookdesigner = (TextView) findViewById(R.id.designerName);
        bookprice160Pages = (TextView) findViewById(R.id.price1);
        bookprice200Pages = (TextView) findViewById(R.id.price2);
        bookprice240Pages = (TextView) findViewById(R.id.price3);
        bookdesc = (TextView) findViewById(R.id.description);
        bookcategory = (TextView) findViewById(R.id.categorySpinner);
        booksubcategory = (TextView) findViewById(R.id.subCategorySpinner);
        bookImage1 = (ImageView) findViewById(R.id.bookImage1);
        bookImage2 = (ImageView) findViewById(R.id.bookImage2);
        bookImage3 = (ImageView) findViewById(R.id.bookImage3);
        bookImage4 = (ImageView) findViewById(R.id.bookImage4);
        bookImage5 = (ImageView) findViewById(R.id.bookImage5);
        bookImage6 = (ImageView) findViewById(R.id.bookImage6);
        bookImage7 = (ImageView) findViewById(R.id.bookImage7);
        addPhotoCard5 = (CardView) findViewById(R.id.addPhotoCard5);
        addPhotoCard6 = (CardView) findViewById(R.id.addPhotoCard6);
        addPhotoCard7 = (CardView) findViewById(R.id.addPhotoCard7);
        uploadPhotos = (Button) findViewById(R.id.uploadPhotos);
        updateText = findViewById(R.id.updateText);

        if (bookCategoryID.matches("Engineering")) {
            addPhotoCard5.setVisibility(View.VISIBLE);
            addPhotoCard6.setVisibility(View.VISIBLE);
            addPhotoCard7.setVisibility(View.VISIBLE);
            imageuri.add("Hello");
            imageuri.add("Hello");
            imageuri.add("Hello");
            totalImages = 7;
        } else {
            totalImages = 4;
            addPhotoCard5.setVisibility(View.GONE);
            addPhotoCard6.setVisibility(View.GONE);
            addPhotoCard7.setVisibility(View.GONE);
        }

        //To get data from Firebase
        getDataFromFirebase();


        bookImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 1;
            }
        });

        bookImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 2;
            }
        });

        bookImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 3;
            }
        });


        bookImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 4;
            }
        });
        bookImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 5;
            }
        });

        bookImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 6;
            }
        });

        bookImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookImage();
                flag = 7;
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

                success(BookDetailsEditActivity.this,"Update Successfull").show();
                Intent x = new Intent(BookDetailsEditActivity.this,MainActivity.class);
                startActivity(x);
                finish();
            }
        });

        uploadPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Uploading Photos");
                progressDialog.show();
                for (int i = 0; i < imageuri.size(); i++){
                    Log.v("BookDetails","i=>"+i);
                    if (imageuri.get(i).matches("Hello")) {

                        if (i==imageuri.size()-1)
                        {
                            progressDialog.dismiss();

                        }
                        continue;
                    }
                    else {
                        Log.v("TAG", "ImageUri:" + imageuri.get(i));
                        uploadImage(Uri.parse(imageuri.get(i)), i, imageuri.size());
                        if (i==imageuri.size()-1)
                        {
                            Toasty.success(getApplicationContext(),"Upload Successfull").show();
                            progressDialog.dismiss();
                            Intent ii = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(ii);
                            finish();
                        }

                    }
                }
            }
        });



    }

    private void getDataFromFirebase() {


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

                //Setting Books
                bookname.setText(bookName);
                bookdesigner.setText(bookDesigner);
                bookdesc.setText(bookDesc);
                bookcategory.setText(bookCategoryID);
                booksubcategory.setText(bookSubCategoryID);
                bookprice160Pages.setText(bookPrice160Pages);
                bookprice200Pages.setText(bookPrice200Pages);
                bookprice240Pages.setText(bookPrice240Pages);
                //To Get images from database and set it cardviews
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        getImagesFromFirebase();


    }

    private void uploadImage(Uri parse, final int i, int size) {
        final int count11 = i + 1;
        final DatabaseReference databaseReference1;
        databaseReference1 = firebaseDatabase.getReference().child("BookDetails")
                .child(bookCategoryID).child(bookSubCategoryID)
                .child(bookID);
        final int max = 10;
        final int min = 1;
        final int random = new Random().nextInt((max-min) + 1) + min;

        final StorageReference reference = mStorageRef.child(bookCategoryID)
                .child(bookSubCategoryID)
                .child(bookID)
                .child("Image"+count11+ "." + getExtension(parse));
        reference.putFile(parse)
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
                                if (count11 == totalImages)
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

    private void getImagesFromFirebase() {
        imagesUrl = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("BookDetails")
                .child(bookCategoryID).child(bookSubCategoryID).child(bookID);
        databaseReference.child("BookImages").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookModal booksmodal = dataSnapshot.getValue(BookModal.class);
                Log.v("TAG","IAmge1:"+booksmodal.getImage1());
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage1())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(bookImage1);
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage2())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(bookImage2);
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage3())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .centerCrop()
                        .into(bookImage3);
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage4())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(bookImage4);
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage5())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(bookImage5);
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage6())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(bookImage6);
                Glide
                        .with(BookDetailsEditActivity.this)
                        .load(booksmodal.getImage7())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(bookImage7);

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setImagesToCards(List<String> imagesUrl) {
        Glide
                .with(BookDetailsEditActivity.this)
                .load(imagesUrl.get(0))
                .centerCrop()
                .into(bookImage1);
        Glide
                .with(BookDetailsEditActivity.this)
                .load(imagesUrl.get(1))
                .centerCrop()
                .into(bookImage2);
        Glide
                .with(BookDetailsEditActivity.this)
                .load(imagesUrl.get(2))
                .centerCrop()
                .into(bookImage3);
        Glide
                .with(BookDetailsEditActivity.this)
                .load(imagesUrl.get(3))
                .centerCrop()
                .into(bookImage4);

        if (bookCategoryID.matches("Engineering"))
        {
            Glide
                    .with(BookDetailsEditActivity.this)
                    .load(imagesUrl.get(4))
                    .centerCrop()
                    .into(bookImage5);
            Glide
                    .with(BookDetailsEditActivity.this)
                    .load(imagesUrl.get(5))
                    .centerCrop()
                    .into(bookImage6);
            Glide
                    .with(BookDetailsEditActivity.this)
                    .load(imagesUrl.get(6))
                    .centerCrop()
                    .into(bookImage7);
        }



        progressDialog.dismiss();
    }

    public void openBookImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
