package com.larkentech.immc2_admin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends Fragment {

    Spinner s1,s2;


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

        s1 = (Spinner) view.findViewById(R.id.categorySpinner);
        s2 = (Spinner)view.findViewById(R.id.subCategorySpinner);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp1= String.valueOf(s1.getSelectedItem());
                if (sp1.contentEquals("Engineering"))
                {
                    List<String> list = new ArrayList<String>();
                    list.add("CS");
                    list.add("EC");
                    list.add("ME");
                    list.add("Chemical Engineering");
                    list.add("Textile Engineering");
                    list.add("EE");
                    list.add("Mining Engineering");
                    list.add("Instrumentation Engineering");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.fragment_add_book,list);
                    dataAdapter.setDropDownViewResource(R.layout.fragment_add_book);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
