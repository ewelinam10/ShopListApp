package com.example.ewmysiak.shoplistapp.ListObjects;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ewmysiak.shoplistapp.Activities.ShopListActivity;
import com.example.ewmysiak.shoplistapp.Helpers.DataBaseHelper;
import com.example.ewmysiak.shoplistapp.Objects.ShopList;
import com.example.ewmysiak.shoplistapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.ArrayList;


public class ShopListAdapter extends FirestoreRecyclerAdapter<ShopList, ShopListHolder> {

    private ArrayAdapter<String> productListAdapter ;
    private ArrayList<String> productList;
    Context context;
    Activity activity;
    AlertDialog.Builder builder;
    DataBaseHelper dataBaseHelper;
    public ShopListAdapter(@NonNull FirestoreRecyclerOptions<ShopList> options,Activity activity) {
        super(options);
        this.activity = activity;
        this.context = activity.getApplicationContext();
        dataBaseHelper = new DataBaseHelper(activity);
    }

    @Override
    public void onBindViewHolder(ShopListHolder holder, int position, final ShopList shopList) {
        dataBaseHelper.setProductCountInLocalDataBase(this.getItemCount());

        productList =  shopList.getProductNameList();
        holder.textTitle.setText(shopList.getCategory());
        holder.textName.setText(shopList.getName());

        ViewGroup.LayoutParams layoutParams = holder.productListView.getLayoutParams();
        layoutParams.height = (int) context.getResources().getDimension(R.dimen.row_nested_list) * productList.size();
        holder.productListView.setLayoutParams(layoutParams);
        productListAdapter = new ArrayAdapter<String>(context, R.layout.product_row, productList);
        holder.productListView.setAdapter(productListAdapter);
        holder.productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,ShopListActivity.class);
                intent.putExtra("shoplist",shopList);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ShopListActivity.class);
                intent.putExtra("shoplist",shopList);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                generateDialog(shopList.getName(),shopList.getShopListUID());
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        holder.productListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                generateDialog(productList.get(position),shopList.getShopListUID());
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            }

        } );
    }
    @Override
    public ShopListHolder onCreateViewHolder(ViewGroup group, int i) {
        context =group.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_product, group, false);
        return new ShopListHolder(view,context);
    }

    private void generateDialog(String titleName, final String shopListUUID) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(titleName);
        String negativeText = context.getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        String[] options = {"Delete", "Show"};
        if(shopListUUID!=null){
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: dataBaseHelper.deleteProductFromList(shopListUUID);
                    }
                }
            });
        }
    }
    @Override
    public void onError(FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }
}
