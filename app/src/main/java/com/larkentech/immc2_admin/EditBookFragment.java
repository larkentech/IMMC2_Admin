package com.larkentech.immc2_admin;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditBookFragment extends Fragment {

    Spinner s1, s2;



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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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

    }
}