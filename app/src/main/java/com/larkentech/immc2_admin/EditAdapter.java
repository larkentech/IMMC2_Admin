package com.larkentech.immc2_admin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class EditAdapter extends ArrayAdapter<BookModal> {

    List<String> bookID;
    Context context;
    Activity activity;
    EditAlertFragment alertDialog;


    public EditAdapter(@NonNull Context context, int resource, @NonNull List<BookModal> objects, List<String> bookId, Activity activity) {
        super(context, resource, objects);
        this.bookID = bookId;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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






        ImageView BookImage = convertView.findViewById(R.id.itemImage);
        Glide
                .with(getContext())
                .load(bookModal.getBookImage())
                .centerCrop()
                .into(BookImage);

        BookPrice160Pages.setText("160 Pages Rs." + bookModal.getBookPrice160Pages() + "/-");
        BookPrice200Pages.setText("200 Pages Rs." + bookModal.getBookPrice200Pages() + "/-");
        BookPrice240Pages.setText("240 Pages Rd." + bookModal.getBookPrice240Pages() + "/-");
        BooksName.setText(bookModal.getBookName());

        BooksDesigner.setText("Designed By "+bookModal.getBookDesigner());


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
                args.putString("BookDesc", bookModal.getBookDesc());
                args.putString("BookCategory", bookModal.getBookCategory());
                args.putString("BookSubCategory", bookModal.getBookSubCategory());


                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                alertDialog = new EditAlertFragment();
                alertDialog.setArguments(args);
                alertDialog.show(fm, "fragment_alert");
            }
        });





        return convertView;
    }
}

