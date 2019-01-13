package com.example.ewmysiak.shoplistapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ewmysiak.shoplistapp.Objects.ShopList;
import com.example.ewmysiak.shoplistapp.R;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ShopList shopList = (ShopList) getIntent().getSerializableExtra("shoplist");
    }
}
