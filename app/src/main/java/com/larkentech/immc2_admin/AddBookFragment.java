package com.larkentech.immc2_admin;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends Fragment {

    Context context;

    Spinner s1,s2;
    ImageView bookImage1;
    ImageView bookImage2;
    ImageView bookImage3;
    ImageView bookImage4;
    ImageView bookImage5;
    ImageView bookImage6;
    ImageView bookImage7;

    CardView addPhotoCard5;
    CardView addPhotoCard6;
    CardView addPhotoCard7;

    EditText bookName;
    EditText designerName;
    EditText price1;
    EditText price2;
    EditText price3;
    EditText description;

    String bookNameStr;
    String bookDesignerNameStr;
    String price1Str;
    String price2Str;
    String price3Str;
    String descriptionStr;
    String bookCategoryStr;
    String bookSubCategoryStr;

    Button createBook;
    Button addCategory;
    Button addSubCategory;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference firebaseDatabase1;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    public static final int IMAGE_CODE=1;

    List<Uri> imageuri = new ArrayList<>();


    String bookPhoto1;
    String bookPhoto2;
    String bookPhoto3;
    String bookPhoto4;
    String bookPhoto5;
    String bookPhoto6;
    String bookPhoto7;

    StorageReference mStorageRef;
    FirebaseStorage storage;
    private int imagesCount = 4;
    HashMap<String, Object> addImagesMap = new HashMap<>();
    Drawable tempdraw;
    String pushKEY;
    String BookImage;

    int count1;
    int imagesCount1;
    HashMap<String, Object> addBookMap = new HashMap<>();


    int flag;

    private List<String> categoryList = new ArrayList<String>();
    private List<String> subCategoryList = new ArrayList<String>();


    public AddBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        context = view.getContext();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStorageRef = FirebaseStorage.getInstance().getReference("NewBooks");

        bookImage1 =(ImageView) view.findViewById(R.id.bookImage1);
        bookImage2 =(ImageView) view.findViewById(R.id.bookImage2);
        bookImage3 =(ImageView) view.findViewById(R.id.bookImage3);
        bookImage4 =(ImageView) view.findViewById(R.id.bookImage4);
        bookImage5 =(ImageView) view.findViewById(R.id.bookImage5);
        bookImage6 =(ImageView) view.findViewById(R.id.bookImage6);
        bookImage7 =(ImageView) view.findViewById(R.id.bookImage7);

        addPhotoCard5 = (CardView) view.findViewById(R.id.addPhotoCard5);
        addPhotoCard6 = (CardView) view.findViewById(R.id.addPhotoCard6);
        addPhotoCard7 = (CardView) view.findViewById(R.id.addPhotoCard7);

        bookName = (EditText) view.findViewById(R.id.bookName);
        price1 = (EditText) view.findViewById(R.id.price1);
        price2 = (EditText) view.findViewById(R.id.price2);
        price3 = (EditText) view.findViewById(R.id.price3);
        description = (EditText) view.findViewById(R.id.description);
        designerName = (EditText) view .findViewById(R.id.designerName);


        createBook = (Button) view.findViewById(R.id.createBook);
        addCategory = (Button) view.findViewById(R.id.addCategory);
        addSubCategory = (Button) view.findViewById(R.id.addSubCategory);


        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCategory();
            }
        });

        addSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSubCategory();
            }
        });

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

        createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateBook();
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();

        s1 = (Spinner) view.findViewById(R.id.categorySpinner);
        s2 = (Spinner)view.findViewById(R.id.subCategorySpinner);

        firebaseDatabase1 = FirebaseDatabase.getInstance().getReference();
        Query query = firebaseDatabase1.child("BookDetails");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    String categoryName = dataSnapshot1.getKey().toString();
                    categoryList.add(categoryName);
                }

                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,categoryList);
                dataAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                s1.setAdapter(dataAdapter1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subCategoryList.clear();

                String sp1= String.valueOf(s1.getSelectedItem());

                if (sp1.equals("Engineering") )
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

                Query query2 = firebaseDatabase1.child("BookDetails").child(sp1);
                query2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            String subCategoryName = dataSnapshot1.getKey().toString();
                            subCategoryList.add(subCategoryName);
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,subCategoryList);
                        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        s2.setAdapter(dataAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

     /*

        FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = firebaseDatabase2.getReference().child("BookDetails");
        HashMap<String,String> engMap = new HashMap<>();
        engMap.put("BookName","How to Crack Test of Arithmetic");
        engMap.put("BookDesigner","Hemanth");
        engMap.put("BookPrice160Pages","450");
        engMap.put("BookPrice200Pages","500");
        engMap.put("BookPrice240Pages","550");
        engMap.put("BookSubCategory","Maths");
        engMap.put("BookCategory","OtherCategories");
        engMap.put("BookDesc","This is a reproduction of a classic text optimised for kindle devices. " +
                "We have endeavoured to create this version as close to the original artefact as possible.");
        engMap.put("BookImage","https://firebasestorage.googleapis.com/v0/b/iammc2-f61a0.appspot.com/o/Arithmemtic.jpg?alt=media&token=29e00124-c5ff-4eec-a237-479ab1b237de");
        pushKEY = databaseReference2.push().getKey();
        engMap.put("BookID",pushKEY);
        databaseReference2.child("OtherCategories").child("Maths").push().setValue(engMap);

      */



    }
    public void openBookImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_CODE && requestCode == RESULT_OK && data != null && data.getData() !=null);

        imageuri.add(data.getData());

        switch (flag)
        {
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


    private String getExtension(Uri uri)
    {

        ContentResolver contentResolver=getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void openCreateBook()
    {
        bookNameStr = bookName.getText().toString();
        bookDesignerNameStr = designerName.getText().toString();
        price1Str = price1.getText().toString();
        price2Str = price2.getText().toString();
        price3Str = price3.getText().toString();
        descriptionStr = description.getText().toString();
        bookCategoryStr = s1.getSelectedItem().toString();
        bookSubCategoryStr = s2.getSelectedItem().toString();

        if (bookNameStr.isEmpty() || bookDesignerNameStr.isEmpty() || price1Str.isEmpty() || descriptionStr.isEmpty() ) {
            Toasty.error(getContext(), "Enter Required Details").show();
        }
        else
            {
                for (int i=0;i<imageuri.size();i++)
                {
                    uploadImage(imageuri.get(i),i,imageuri.size());
                }


                databaseReference = firebaseDatabase.getReference();
                addBookMap.put("BookName", bookName.getText().toString());
                addBookMap.put("BookPrice160Pages", price1.getText().toString());
                addBookMap.put("BookPrice200Pages", price1.getText().toString());
                addBookMap.put("BookPrice240Pages", price1.getText().toString());
                addBookMap.put("BookDesc", description.getText().toString());
                addBookMap.put("BookDesigner", designerName.getText().toString());
                addBookMap.put("BookCategory",s1.getSelectedItem().toString());
                addBookMap.put("BookSubCategory",s2.getSelectedItem().toString());


                pushKEY = databaseReference.push().getKey();
                addBookMap.put("BookId",pushKEY);

            }
    }

    public void uploadImage(Uri uri, int count, final int imagesCount)
    {

        final int count11 = count+1;


        final StorageReference reference=mStorageRef.child("image"+Integer.toString(count)+"."+getExtension(imageuri.get(count)));
        reference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                addImagesMap.put("Image"+count11,uri.toString());
                                addBookMap.put("BookImage",addImagesMap.get("Image1"));
                                Toasty.success(getContext(), "Book Added Successfully").show();
                                if (count11 == imagesCount)
                                {
                                    FirebaseDatabase firebaseDatabase11 = FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference11 = firebaseDatabase11.getReference();
                                    DatabaseReference databaseReference12 = firebaseDatabase11.getReference();
                                    databaseReference12.child("BookDetails").child(bookCategoryStr).child(bookSubCategoryStr).child(pushKEY).setValue(addBookMap);
                                    databaseReference11.child("BookDetails").child(bookCategoryStr).child(bookSubCategoryStr).child(pushKEY).child("BookImages").setValue(addImagesMap);
                                    Toasty.error(getContext(), "Book Added Successfully").show();
                                    Intent i = new Intent(getActivity(), MainActivity.class);
                                    startActivity(i);
                                    getActivity().finish();
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

    public void openAddCategory(){
        final AlertDialog.Builder  alert = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.add_category_dialog,null);
        final EditText add_category = (EditText) mView.findViewById(R.id.addNewCategory);
        Button addCategoryBtn = (Button) mView.findViewById(R.id.addCategoryBtn);
        Button cancelCategoryBtn = (Button) mView.findViewById(R.id.cancelCategoryBtn);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_category == null){
                    Toasty.error(getContext(),"Enter Category").show();
                }else{
                    categoryList.add(add_category.getText().toString());
                    Toasty.success(getContext(),"Category Added").show();
                }
            }
        });

        cancelCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    public void openAddSubCategory(){

        final AlertDialog.Builder  alert = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.add_category_dialog,null);
        final EditText add_sub_category = (EditText) mView.findViewById(R.id.addNewSubCategory);
        Button addSubCategoryBtn = (Button) mView.findViewById(R.id.addSubCategoryBtn);
        Button cancelSubCategoryBtn = (Button) mView.findViewById(R.id.cancelSubCategoryBtn);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        addSubCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_sub_category == null){
                    Toasty.error(getContext(),"Enter Sub Category").show();
                }else{
                    subCategoryList.add(add_sub_category.getText().toString());
                    Toasty.success(getContext(),"Sub Category Added").show();
                }
            }
        });

        cancelSubCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
        
    }



}
