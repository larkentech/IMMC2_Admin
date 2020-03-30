package com.larkentech.immc2_admin;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditBookFragment extends Fragment {

    Spinner s1, s2;
    ListView selectedListView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    DatabaseReference firebaseDatabase1;

    private List<String> categoryList = new ArrayList<String>();
    private List<String> subCategoryList = new ArrayList<String>();

    EditAdapter editAdapter;

    List<String> BookID;

    Button getBook;
    public String bookID;
    public String bookCategoryID;
    public String bookSubCategoryID;

    public EditBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_edit_book, container, false);


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBook = view.findViewById(R.id.getBook);
        BookID = new ArrayList<>();

        s1 = (Spinner) view.findViewById(R.id.categorySpinner);
        s2 = (Spinner) view.findViewById(R.id.subCategorySpinner);

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

                Query query2 = firebaseDatabase1.child("CategoryImages").child(sp1);
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


        getBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String category = s1.getSelectedItem().toString();
                String subCategory = s2.getSelectedItem().toString();

                selectedListView = view.findViewById(R.id.editListView);
                List<BookModal> selectedList = new ArrayList<>();
                editAdapter = new EditAdapter(getContext(), R.layout.single_edit_item, selectedList, BookID, getActivity(),EditBookFragment.this);
                selectedListView.setAdapter(editAdapter);

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("BookDetails").child(category).child(subCategory);
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        try {
                            BookModal selectedBookModal = dataSnapshot.getValue(BookModal.class);
                            BookID.add(dataSnapshot.getKey());
                            editAdapter.add(selectedBookModal);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
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
            }
        });
    }
}




