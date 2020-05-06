package com.larkentech.immc2_admin.Fragments;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.larkentech.immc2_admin.Adapters.ActivityTrackerAdapter;
import com.larkentech.immc2_admin.MainActivity;
import com.larkentech.immc2_admin.ModalClasses.ActivityTrackerModal;
import com.larkentech.immc2_admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;
import static com.larkentech.immc2_admin.Fragments.AddBookFragment.IMAGE_CODE;


public class ActivityTrackerFragment extends Fragment {

    private static final int IMAGE_CODE = 1;
    ImageView trackerImage;
    TextView trackerName;
    Button getBooks;
    ListView trackerList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseDatabase trackerFirebaseDatabase;
    DatabaseReference databaseReferenceTracker;

    ActivityTrackerAdapter adapter;

    List<String> bookID;
    List<String> category;
    List<String> subcategory;

    String _trackerName;
    Uri uri;

    boolean flag = false;
    StorageReference mStorageRef;
    ProgressDialog progressDialog;


    public ActivityTrackerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_tracker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStorageRef = FirebaseStorage.getInstance().getReference("ActivityTrackers");
        progressDialog = new ProgressDialog(getContext());

       /* FirebaseDatabase firebaseDatabaseTracker = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReferenceTracker = firebaseDatabaseTracker.getReference().child("BookDetails");
        databaseReferenceTracker.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot ds:dataSnapshot.getChildren())
                {
                    databaseReferenceTracker.child(ds.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {//Category
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (final DataSnapshot dss:dataSnapshot.getChildren())
                            {
                                databaseReferenceTracker.child(ds.getKey()).child(dss.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {//Subcategory
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dsss:dataSnapshot.getChildren())
                                        {
                                            HashMap<String,String> booksMap = new HashMap<>();
                                            booksMap.put("BookName",dsss.child("BookName").getValue().toString());
                                            booksMap.put("BookImage",dsss.child("BookImage").getValue().toString());
                                            booksMap.put("Category",ds.getKey());
                                            booksMap.put("SubCategory",dss.getKey());
                                            booksMap.put("BookID",dsss.getKey());

                                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                            DatabaseReference databaseReference = firebaseDatabase.getReference().child("BooksListNames");
                                            databaseReference.child(dsss.getKey()).setValue(booksMap);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); */

        bookID = new ArrayList<>();
        category = new ArrayList<>();
        subcategory = new ArrayList<>();

        trackerImage = view.findViewById(R.id.activityTrackerImage);
        trackerName = view.findViewById(R.id.trackerCategoryEt);
        getBooks = view.findViewById(R.id.getBookList);
        trackerList = view.findViewById(R.id.booksListTracker);
        trackerList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        List<ActivityTrackerModal> list = new ArrayList<>();
        adapter = new ActivityTrackerAdapter(getContext(),R.layout.single_book_list_tracker,list);
        trackerList.setAdapter(adapter);

        trackerFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceTracker = trackerFirebaseDatabase.getReference().child("BooksListNames");
        databaseReferenceTracker.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ActivityTrackerModal modal = dataSnapshot.getValue(ActivityTrackerModal.class);
                adapter.add(modal);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        trackerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                openBookImage();
            }
        });


        getBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _trackerName = trackerName.getText().toString();
                if (_trackerName.matches("") && flag)
                {
                    Toasty.error(getContext(),"Please Enter the Subcategory Name").show();
                }
                else {
                    for (int i = 0; i < trackerList.getCount(); i++) {
                        v = trackerList.getAdapter().getView(i, null, null);
                        CheckBox checkbox = v.findViewById(R.id.checkBoxList);
                        TextView bookName = v.findViewById(R.id.bookNameTracker);

                        if (checkbox.isChecked())
                        {

                            bookID.add(adapter.SelectedBook.get(i));
                            category.add(adapter.selectedBookCategory.get(i));
                            subcategory.add(adapter.selectedBookSubCategory.get(i));
                        }


                    }

                    Log.v("TAG","BooksCount=>"+bookID.size());

                    if (bookID.size() == 0) {
                        Toasty.error(getContext(),"Please select books to add.").show();
                    }
                    else {

                        Log.v("TAG","BookID=>"+bookID);

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


                        if (flag){
                            uploadImage();
                            DatabaseReference databaseReference = firebaseDatabase.getReference();
                            HashMap<String,String> activityTrackers = new HashMap<>();
                            for (int i=0;i<bookID.size();i++)
                            {
                                activityTrackers.put("BookID",bookID.get(i));
                                activityTrackers.put("BookCategory",category.get(i));
                                activityTrackers.put("BookSubCategory",subcategory.get(i));
                                databaseReference.child("Activity Trackers").child(_trackerName).child(bookID.get(i)).setValue(activityTrackers);

                                Toasty.success(getContext(),"Activity added successfully").show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        }
                        else {
                            Toasty.error(getContext(),"Please Select an image for category").show();
                        }



                    }
                }

            }


        });

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
        Log.d("requestCode", String.valueOf(requestCode));
        Log.d("dat",String.valueOf(data));
        Log.d("res",String.valueOf(RESULT_OK));
        Log.d("data",String.valueOf(data.getData()));
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            trackerImage.setImageURI(data.getData());
            uri = data.getData();
            flag =true;
        }
    }

    public void uploadImage(){
        progressDialog.setTitle("Uploading Photo");
        progressDialog.show();
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
                                DatabaseReference databaseReference = trackerFirebaseDatabase.getReference();
                                databaseReference.child("ActivityTrackersImages").child(_trackerName).setValue(uri.toString());
                                progressDialog.dismiss();
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

    private String getExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}
