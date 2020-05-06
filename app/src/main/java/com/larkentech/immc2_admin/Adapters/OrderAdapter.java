package com.larkentech.immc2_admin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.larkentech.immc2_admin.ModalClasses.OrderModal;
import com.larkentech.immc2_admin.Fragments.OrdersFragment;
import com.larkentech.immc2_admin.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<OrderModal> {

    List<String> tnxID;
    Context context;
    Fragment fragment;


    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<OrderModal> objects, List<String> tnxId, OrdersFragment ordersFragment) {
        super(context, resource, objects);
        this.tnxID = tnxId;
        this.context = context;
        this.fragment = fragment;

    }

    @Nullable
    @Override
    public OrderModal getItem(int position) {
        return super.getItem(getCount() - position - 1);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_order, parent, false);

        }
        final OrderModal orderModal = getItem(position);

        CardView selected = convertView.findViewById(R.id.orderCard);
        TextView BookName = convertView.findViewById(R.id.bookName);
        TextView Address = convertView.findViewById(R.id.address);
        TextView FinalPrice = convertView.findViewById(R.id.bookPrice);
        TextView PhoneNumber = convertView.findViewById(R.id.phno);
        TextView ItemsCount = convertView.findViewById(R.id.quantity);
        TextView OrderDate = convertView.findViewById(R.id.orderDate);
        TextView TxnID = convertView.findViewById(R.id.txnNum);
        TextView pages = convertView.findViewById(R.id.pages);


        BookName.setText(orderModal.getBookName());
        Address.setText(orderModal.getAddress());
        FinalPrice.setText(orderModal.getFinalPrice() + " Paid");
        ItemsCount.setText("Qty: " + orderModal.getItemsCount());
        PhoneNumber.setText("Ph: "+ orderModal.getPhoneNumber());
        OrderDate.setText(orderModal.getOrderDate());
        TxnID.setText(orderModal.getTxnID());
        pages.setText(orderModal.getPages());

        return convertView;
    }



}
