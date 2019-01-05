package com.example.ewmysiak.shoplistapp.ListObjects;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ewmysiak.shoplistapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    TextView textName;
    @BindView(R.id.title)
    TextView textTitle;
    @BindView(R.id.productList)
    ListView productListView;
    public ShopListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}