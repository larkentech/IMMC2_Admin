package com.larkentech.immc2_admin.Fragments;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.larkentech.immc2_admin.R;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends DialogFragment {

    EditText addNewSubCategory;
    ImageView subCategoryImage;
    Button addSubCategoryBtn;
    Button cancelSubCategoryBtn;

    String addNewSubCategoryStr;
    String imageUrl;

    Uri imageUri;
    StorageReference mStorageRef;

    HashMap<String, Object> addImagesMap = new HashMap<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference=firebaseDatabase.getInstance().getReference().child("CategoryImages");

    public String bookCategoryID;

    public static final int IMAGE_CODE=1;

    public SubCategoryFragment() {
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

        bookCategoryID = getArguments().getString("BookCategory");

        return inflater.inflate(R.layout.fragment_sub_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.v("SubCategoryFragment","BookCategory:"+bookCategoryID);

        mStorageRef = FirebaseStorage.getInstance().getReference("New Books");

        addNewSubCategory = (EditText) view.findViewById(R.id.addNewSubCategory);
        subCategoryImage = (ImageView) view.findViewById(R.id.subCategoryImage);
        addSubCategoryBtn = (Button) view.findViewById(R.id.addSubCategoryBtn);
        cancelSubCategoryBtn = (Button) view.findViewById(R.id.cancelSubCategoryBtn);

        subCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectImage();
            }
        });

        addSubCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSubCategoryStr = addNewSubCategory.getText().toString();
                addImagesMap.put(addNewSubCategoryStr,imageUrl);
                databaseReference.child(bookCategoryID).updateChildren(addImagesMap);
                Toasty.success(getContext(),"Sub Category added Successfully").show();
                getDialog().dismiss();

            }
        });


        cancelSubCategoryBtn.setOnClickListener(new View.OnClickListener() {
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
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    }

    private String getExtension(Uri uri)
    {

        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void openSelectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null && data.getData() !=null);

        imageUri = data.getData();
        subCategoryImage.setImageURI(imageUri);

        final StorageReference reference=mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imageUri));

        reference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageUrl = uri.toString();

                            }
                        });

                        Toasty.success(getContext(),"Image Uploaded Successfully").show();
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
