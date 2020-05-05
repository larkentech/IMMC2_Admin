package com.larkentech.immc2_admin.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.larkentech.immc2_admin.MainActivity;
import com.larkentech.immc2_admin.R;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class ComingSoonFragment extends Fragment {

    EditText productName;
    EditText displayName;
    EditText description;
    EditText date;
    ImageView productImage;
    ImageView displayImage;
    Button addComingSoon;
    String dateStr;
    String descStr;
    String imageUrl1;
    String imageUrl2;

    final Calendar myCalendar = Calendar.getInstance();

    String pushKey;

    int flag;

    Uri imageUri1;
    Uri imageUri2;

    String productNameStr;
    String displayNameStr;

    StorageReference storageReference;


    HashMap<String, Object> addBookMap = new HashMap<>();
    HashMap<String, Object> addImagesMap = new HashMap<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    ProgressDialog progress;

    public static final int IMAGE_CODE=1;

    public ComingSoonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coming_soon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storageReference = FirebaseStorage.getInstance().getReference("ComingSoon");

        productName = (EditText) view.findViewById(R.id.productName);
        displayName = (EditText) view.findViewById(R.id.displayName);
        description = (EditText) view.findViewById(R.id.description);
        displayImage = (ImageView) view.findViewById(R.id.displayImage);
        productImage = (ImageView) view.findViewById(R.id.productImage);
        addComingSoon = (Button) view.findViewById(R.id.addComingSoonBtn);
        date = (EditText) view.findViewById(R.id.date);

        progress = new ProgressDialog(getContext());
        progress.setTitle("Adding Coming soon offer");
        progress.setCancelable(false);

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                openImage();

            }
        });

        displayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                openImage();

            }
        });

        addComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                productNameStr = productName.getText().toString();
                displayNameStr = displayName.getText().toString();
                dateStr = date.getText().toString();
                descStr = description.getText().toString();

                if (productNameStr.isEmpty() || displayNameStr.isEmpty() || dateStr.isEmpty() || descStr.isEmpty())
                {
                    Toasty.error(getContext(),"Enter required details").show();
                }else{

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference();
                    addBookMap.put("ReleaseDate", date.getText().toString());
                    addBookMap.put("ProductName", productName.getText().toString());
                    addBookMap.put("DisplayName", displayName.getText().toString());
                    addBookMap.put("Description", description.getText().toString());

                    switch (flag)
                    {
                        case 0:
                            final StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getExtension(imageUri1));

                            reference.putFile(imageUri1)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    imageUrl1 = uri.toString();
                                                    addBookMap.put("ProductImage",imageUrl1);
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
                            break;

                        case 1:
                            final StorageReference reference1=storageReference.child(System.currentTimeMillis()+"."+getExtension(imageUri2));

                            reference1.putFile(imageUri1)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    imageUrl2 = uri.toString();
                                                    addBookMap.put("DisplayImage",imageUrl2);

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
                            break;

                    }

                    databaseReference.child("ComingSoon").push().setValue(addBookMap);
                    progress.dismiss();
                    Toasty.success(getContext(),"Added Successfully").show();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            }
        });


        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void openImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_CODE && requestCode == RESULT_OK && data != null && data.getData() !=null);

        switch (flag)
        {
            case 0:
                imageUri1 = data.getData();
                productImage.setImageURI(imageUri1);
                break;

            case 1:
                imageUri2 = data.getData();
                displayImage.setImageURI(imageUri2);
        }

    }

    private String getExtension(Uri uri)
    {
        ContentResolver contentResolver=getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }
}
