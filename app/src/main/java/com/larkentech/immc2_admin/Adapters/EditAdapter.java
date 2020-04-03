package com.larkentech.immc2_admin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.larkentech.immc2_admin.AlertFragments.EditAlertFragment;
import com.larkentech.immc2_admin.ModalClasses.BookModal;
import com.larkentech.immc2_admin.R;

import java.util.List;

public class EditAdapter extends ArrayAdapter<BookModal> {

    List<String> bookID;
    Context context;
    Activity activity;
    EditAlertFragment alertDialog;
    BookModal booksmodal;
    Bundle args;
    Fragment fragment;



    public EditAdapter(@NonNull Context context, int resource, @NonNull List<BookModal> objects, List<String> bookId, Activity activity, Fragment fragment) {
        super(context, resource, objects);
        this.bookID = bookId;
        this.context = context;
        this.activity = activity;
        this.fragment = fragment;
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
                .into(BookImage);


        booksmodal = new BookModal();
        args = new Bundle();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("BookDetails")
                .child(bookModal.getBookCategory()).child(bookModal.getBookSubCategory())
                .child(bookModal.getBookID());
        databaseReference.child("BookImages").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                booksmodal = dataSnapshot.getValue(BookModal.class);
                args.putString("Image1",bookModal.getBookImage());
                args.putString("Image2",booksmodal.getImage2());
                args.putString("Image3",booksmodal.getImage3());
                args.putString("Image4",booksmodal.getImage4());

                if (bookModal.getBookCategory().matches("Engineering"))
                {
                    args.putString("Image5",booksmodal.getImage5());
                    args.putString("Image6",booksmodal.getImage6());
                    args.putString("Image7",booksmodal.getImage7());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                args.putString("BookImage", bookModal.getBookImage());
                args.putString("BookName", bookModal.getBookName());
                args.putString("BookDesigner", bookModal.getBookDesigner());
                args.putString("BookPrice160Pages", bookModal.getBookPrice160Pages());
                args.putString("BookPrice200Pages",bookModal.getBookPrice200Pages());
                args.putString("BookPrice240Pages",bookModal.getBookPrice240Pages());
                args.putString("BookDesc", bookModal.getBookDesc());
                args.putString("BookID",bookModal.getBookID());
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

