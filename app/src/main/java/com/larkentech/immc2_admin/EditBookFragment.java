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

    EditAdapter editAdapter;

    List<String> BookId;

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
        BookId = new ArrayList<>();

        s1 = (Spinner) view.findViewById(R.id.categorySpinner);
        s2 = (Spinner) view.findViewById(R.id.subCategorySpinner);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp1 = String.valueOf(s1.getSelectedItem());
                if (sp1.contentEquals("Engineering")) {
                    List<String> list = new ArrayList<String>();
                    list.add("CS");
                    list.add("EC");
                    list.add("ME");
                    list.add("Chemical Engineering");
                    list.add("Textile Engineering");
                    list.add("EE");
                    list.add("Mining Engineering");
                    list.add("Instrumentation Engineering");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
                    dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter);

                }
                if (sp1.contentEquals("MathsTheme")) {
                    List<String> list = new ArrayList<String>();
                    list.add("AlgebraNoteBooks");
                    list.add("ArithmeticNoteBooks");
                    list.add("GeometryNoteBooks");
                    list.add("TrigonometryNoteBooks");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
                    dataAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter2);

                }
                if (sp1.contentEquals("QuoteTheme")) {
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
                    ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
                    dataAdapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter3.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter3);

                }
                if (sp1.contentEquals("ScienceTheme")) {
                    List<String> list = new ArrayList<>();
                    list.add("BiologyNoteBooks");
                    list.add("PhysicsNoteBooks");
                    list.add("ChemistryNoteBooks");
                    ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
                    dataAdapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dataAdapter4.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter4);

                }
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
                editAdapter = new EditAdapter(getContext(), R.layout.single_edit_item, selectedList, BookId, getActivity());
                selectedListView.setAdapter(editAdapter);


                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("BookDetails").child(category).child(subCategory);
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        BookModal selectedBookModal = dataSnapshot.getValue(BookModal.class);
                        editAdapter.add(selectedBookModal);
                        BookId.add(dataSnapshot.getKey());
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




