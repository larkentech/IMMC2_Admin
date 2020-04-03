package com.larkentech.immc2_admin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.larkentech.immc2_admin.BookDetailsEditActivity;
import com.larkentech.immc2_admin.ModalClasses.BookModal;
import com.larkentech.immc2_admin.Fragments.EditAlertFragmentRevised;
import com.larkentech.immc2_admin.R;

import java.util.List;

public class EditAdapterRevised extends ArrayAdapter<BookModal> {

    List<String> bookID;
    Context context;
    Activity activity;
    EditAlertFragmentRevised alertDialog;
    BookModal booksmodal;
    Bundle args;
    Fragment fragment;

    String category;
    String subcategory;
    String _bookId;

    public EditAdapterRevised(@NonNull Context context, int resource, @NonNull List<BookModal> objects, List<String> bookId) {
        super(context, resource, objects);
        this.bookID = bookId;
        this.context = context;
        this.activity = activity;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_edit_item, parent, false);
        }

        final BookModal bookModal = getItem(position);

        CardView selected = convertView.findViewById(R.id.editCard);
        TextView BookPrice160Pages = convertView.findViewById(R.id.bookPrice);
        TextView BookPrice200Pages = convertView.findViewById(R.id.bookPrice1);
        TextView BookPrice240Pages = convertView.findViewById(R.id.bookPrice2);
        TextView BooksName = convertView.findViewById(R.id.bookName);
        TextView BooksDesigner = convertView.findViewById(R.id.bookDesigner);
        TextView BookDesc = convertView.findViewById(R.id.bookDesc);

        ImageView BookImage = convertView.findViewById(R.id.itemImage);
        BookPrice160Pages.setText("Rs." + bookModal.getBookPrice160Pages() + "/-");
        BookPrice200Pages.setText("Rs." + bookModal.getBookPrice200Pages() + "/-");
        BookPrice240Pages.setText("Rs." + bookModal.getBookPrice240Pages() + "/-");
        BooksName.setText(bookModal.getBookName());
        BookDesc.setText(bookModal.getBookDesc());
        BooksDesigner.setText("Designed By "+bookModal.getBookDesigner());

        Glide
                .with(getContext())
                .load(bookModal.getBookImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(BookImage);

        category = bookModal.getBookCategory();
        subcategory = bookModal.getBookSubCategory();
        _bookId = bookModal.getBookID();

        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Revised","Args:=>"+args);
                Intent i = new Intent(getContext(), BookDetailsEditActivity.class);
                i.putExtra("Category",category);
                i.putExtra("Subcategory",subcategory);
                i.putExtra("BookId",_bookId);
                getContext().startActivity(i);

            }
        });





        return convertView;
    }
}
