package com.larkentech.immc2_admin;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class FeedbackAdapter extends ArrayAdapter<FeedbackModal> {


    Context context;
    Fragment fragment;



    public FeedbackAdapter(@NonNull Context context, int resource, @NonNull List<FeedbackModal> objects, Fragment fragment) {
        super(context, resource, objects);

        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.single_feedback,parent,false);
        }


        final FeedbackModal feedbackModal = getItem(position);

        TextView Feedback = convertView.findViewById(R.id.feedbackMsg);
        TextView Feeling = convertView.findViewById(R.id.feeling);

        Feedback.setText(feedbackModal.getFeedback());
        Feeling.setText(feedbackModal.getFeeling());

        return convertView;
    }
}
