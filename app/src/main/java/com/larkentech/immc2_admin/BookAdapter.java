package com.larkentech.immc2_admin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends ArrayAdapter<BookModal> {

    List<String> bookID;
    Context context;
    Activity activity;
    DeleteAlertFragment alertDialog;


    public BookAdapter(@NonNull Context context, int resource, @NonNull List<BookModal> objects, List<String> bookId, Activity activity) {
        super(context, resource, objects);
        this.bookID = bookId;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_delete_item, parent, false);
        }


        final BookModal bookModal = getItem(position);


        CardView selected = convertView.findViewById(R.id.deleteCard);
        TextView BookPrice = convertView.findViewById(R.id.bookPrice);
        TextView BookPrice1 = convertView.findViewById(R.id.bookPrice1);
        TextView BookPrice2 = convertView.findViewById(R.id.bookPrice2);
        TextView BooksName = convertView.findViewById(R.id.bookName);
        TextView BooksDesigner = convertView.findViewById(R.id.bookDesigner);
        TextView BookDesc = convertView.findViewById(R.id.bookDesc);

        ImageView BookImage = convertView.findViewById(R.id.itemImage);
        Glide
                .with(getContext())
                .load(bookModal.getBookImage())
                .centerCrop()
                .into(BookImage);

        BookPrice.setText("Rs." + bookModal.getBookPrice160Pages() + "/-");
        BookPrice1.setText("Rs." + bookModal.getBookPrice200Pages() + "/-");
        BookPrice2.setText("Rs." + bookModal.getBookPrice240Pages() + "/-");
        BooksName.setText(bookModal.getBookName());
        BooksDesigner.setText("Designed By "+bookModal.getBookDesigner());
        BookDesc.setText(bookModal.getBookDesc());

        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("BookImage", bookModal.getBookImage());
                args.putString("BookName", bookModal.getBookName());
                args.putString("BookDesigner", bookModal.getBookDesigner());
                args.putString("BookPrice160Pages", bookModal.getBookPrice160Pages());
                args.putString("BookPrice200Pages",bookModal.getBookPrice200Pages());
                args.putString("BookPrice240Pages",bookModal.getBookPrice240Pages());



                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                alertDialog = new DeleteAlertFragment();
                alertDialog.setArguments(args);
                alertDialog.show(fm, "fragment_alert");

            }
        });





        return convertView;
    }
}

