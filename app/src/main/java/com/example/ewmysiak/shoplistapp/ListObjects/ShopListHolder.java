package com.example.ewmysiak.shoplistapp.ListObjects;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ewmysiak.shoplistapp.Activities.ShopListActivity;
import com.example.ewmysiak.shoplistapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.name)
    TextView textName;
    @BindView(R.id.title)
    TextView textTitle;
    @BindView(R.id.productList)
    ListView productListView;
    @BindView(R.id.itemProductLayout)
    LinearLayout itemProductLayout;

    private Context context;

    public ShopListHolder(View itemView, final Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        productListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

}