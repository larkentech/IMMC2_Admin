package com.larkentech.immc2_admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.larkentech.immc2_admin.Adapters.ActivityTrackerAdapter;
import com.larkentech.immc2_admin.ModalClasses.ActivityTrackerModal;
import com.larkentech.immc2_admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityTrackerFragment extends Fragment {

    ImageView trackerImage;
    TextView trackerName;
    Button getBooks;
    ListView trackerList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseDatabase trackerFirebaseDatabase;
    DatabaseReference databaseReferenceTracker;

    ActivityTrackerAdapter adapter;


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


        getBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> names = new ArrayList<String>();

                SparseBooleanArray checkedPositions = trackerList.getCheckedItemPositions ();
                int size = checkedPositions.size ();
                for (int i=0 ; i<size ; i++) {
                    // We get the key stored at the index 'i'
                    int key = checkedPositions.keyAt (i);
                    // We get the boolean value with the key
                    Log.i ("Tag", "checkedPositions(" + key + ")=" + checkedPositions.get (key));
                }

            }
        });





    }
}
