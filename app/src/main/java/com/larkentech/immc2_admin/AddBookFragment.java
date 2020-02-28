package com.larkentech.immc2_admin;


import android.content.ContentResolver;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    EditText price;
    EditText description;

    String bookNameStr;
    String bookDesignerNameStr;
    String priceStr;
    String descriptionStr;
    String bookCategoryStr;
    String bookSubCategoryStr;

    Button createBook;
    FirebaseDatabase firebaseDatabase;
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


    public AddBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false);

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
        price = (EditText) view.findViewById(R.id.price);
        description = (EditText) view.findViewById(R.id.description);
        designerName = (EditText) view .findViewById(R.id.designerName);


        createBook = (Button) view.findViewById(R.id.createBook);


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
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp1= String.valueOf(s1.getSelectedItem());
                if (sp1.contentEquals("Engineering"))
                {
                    imagesCount = 7;
                    List<String> list = new ArrayList<String>();
                    list.add("CS");
                    list.add("EC");
                    list.add("ME");
                    list.add("Chemical Engineering");
                    list.add("Textile Engineering");
                    list.add("EE");
                    list.add("Mining Engineering");
                    list.add("Instrumentation Engineering");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
                    dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter);
                    addPhotoCard5.setVisibility(View.VISIBLE);
                    addPhotoCard6.setVisibility(View.VISIBLE);
                    addPhotoCard7.setVisibility(View.VISIBLE);
                }
                if (sp1.contentEquals("MathsTheme"))
                {
                    List<String> list = new ArrayList<String>();
                    list.add("AlgebraNoteBooks");
                    list.add("ArithmeticNoteBooks");
                    list.add("GeometryNoteBooks");
                    list.add("TrigonometryNoteBooks");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
                    dataAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter2);
                    addPhotoCard5.setVisibility(View.GONE);
                    addPhotoCard6.setVisibility(View.GONE);
                    addPhotoCard7.setVisibility(View.GONE);
                }
                if (sp1.contentEquals("QuoteTheme"))
                {
                    List<String> list = new ArrayList<>();
                    list.add("APJ NoteBooks");
                    list.add("AlbertEinsteinQuoteSeries");
                    list.add("BillGatesQuoteSeries");
                    list.add("BuddhaQuoteSeries");
                    list.add("ElonMuskQuoteSeries");
                    list.add("RamanujanQuoteSeries");
                    list.add("SteveJobsQuoteSeries");
                    list.add("Subhash Chandra bose Notebooks");
                    list.add("Vivekananda Notebooks");
                    ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
                    dataAdapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter3.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter3);
                    addPhotoCard5.setVisibility(View.GONE);
                    addPhotoCard6.setVisibility(View.GONE);
                    addPhotoCard7.setVisibility(View.GONE);
                }
                if (sp1.contentEquals("ScienceTheme"))
                {
                    List<String> list = new ArrayList<>();
                    list.add("BiologyNoteBooks");
                    list.add("PhysicsNoteBooks");
                    list.add("ChemistryNoteBooks");
                    ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
                    dataAdapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter4.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter4);
                    addPhotoCard5.setVisibility(View.GONE);
                    addPhotoCard6.setVisibility(View.GONE);
                    addPhotoCard7.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        priceStr = price.getText().toString();
        descriptionStr = description.getText().toString();
        bookCategoryStr = s1.getSelectedItem().toString();
        bookSubCategoryStr = s2.getSelectedItem().toString();

        if (bookNameStr.isEmpty() || bookDesignerNameStr.isEmpty() || priceStr.isEmpty() || descriptionStr.isEmpty() ) {
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
                addBookMap.put("BookPrice", price.getText().toString());
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

}
