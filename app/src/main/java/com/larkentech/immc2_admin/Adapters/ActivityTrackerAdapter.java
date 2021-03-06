package com.larkentech.immc2_admin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.larkentech.immc2_admin.ModalClasses.ActivityTrackerModal;
import com.larkentech.immc2_admin.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ActivityTrackerAdapter extends ArrayAdapter<ActivityTrackerModal> {

    List<ActivityTrackerModal> listData;
    boolean[] _checkbox;
    public List<String> SelectedBook = new ArrayList<>();
    public List<String> selectedBookCategory = new ArrayList<>();
    public List<String> selectedBookSubCategory = new ArrayList<>();


    public ActivityTrackerAdapter(@NonNull Context context, int resource, @NonNull List<ActivityTrackerModal> objects) {
        super(context, resource, objects);
        this.listData = objects;
        _checkbox = new boolean[100];
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_book_list_tracker, parent, false);
        }

        final ActivityTrackerModal modal = getItem(position);

        selectedBookCategory.add(modal.getBookCategory());
        selectedBookSubCategory.add(modal.getBookSubCategory());
        SelectedBook.add(modal.getBookID());

        ImageView image = convertView.findViewById(R.id.singleBookImageTracker);
        TextView name = convertView.findViewById(R.id.bookNameTracker);
        TextView subcategory = convertView.findViewById(R.id.bookCategoryNameTracker);
        final CheckBox checkBox = convertView.findViewById(R.id.checkBoxList);

        if (modal.getChecked()){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }
        name.setText(modal.getBookName());
        subcategory.setText(modal.getBookSubCategory());
        Glide
                .with(getContext())
                .load(modal.getBookImage())
                .centerCrop()
                .into(image);
      /*  checkBox.setChecked(_checkbox[position]);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.v("Adapter","Position:"+position);

            }});*/

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modal.getChecked()){
                    checkBox.setChecked(false);
                    modal.setChecked(false);
                    _checkbox[position] = false;
                }else{
                    checkBox.setChecked(true);
                    modal.setChecked(true);
                    _checkbox[position] = true;
                }
            }
        });

        return convertView;
    }
}
