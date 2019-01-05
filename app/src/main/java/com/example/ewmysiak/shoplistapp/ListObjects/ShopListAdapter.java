package com.example.ewmysiak.shoplistapp.ListObjects;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.ewmysiak.shoplistapp.Objects.Product;
import com.example.ewmysiak.shoplistapp.Objects.ShopList;
import com.example.ewmysiak.shoplistapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopListAdapter extends FirestoreRecyclerAdapter<ShopList, ShopListHolder> {

    private ArrayAdapter<String> productListAdapter ;
    private ArrayList<String> productList;
    Context context;

    public ShopListAdapter(@NonNull FirestoreRecyclerOptions<ShopList> options) {
        super(options);
    }

    @Override
    public void onBindViewHolder(ShopListHolder holder, int position, ShopList model) {
        //progressBar.setVisibility(View.GONE);
        productList =  model.getProductNameList();
        productListAdapter = new ArrayAdapter<String>(context, R.layout.product_row, productList);
        holder.productListView.setAdapter(productListAdapter);

        holder.textTitle.setText(model.getCategory());
        holder.textName.setText(model.getName());
        //holder.textTitle.setText(list.get(0).getProductName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public ShopListHolder onCreateViewHolder(ViewGroup group, int i) {
        context =group.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_product, group, false);
        return new ShopListHolder(view);
    }

    @Override
    public void onError(FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }
}
