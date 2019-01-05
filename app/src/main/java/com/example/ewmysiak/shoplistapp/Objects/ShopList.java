package com.example.ewmysiak.shoplistapp.Objects;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class ShopList {
    public enum ShopListCategory {
        DAILY,
        WEEKLY,
        MONTHLY;
    }
    private String name;
    private String category;
    private String userUID;
    private ArrayList<Product> productList;

    public ShopList() {
    }

    public ShopList(String name,String category,ArrayList<Product> productList,String userUID) {
        this.name = name;
        this.category = category;
        this.productList = productList;
        this.userUID = userUID;
    }

    public ArrayList<String> getProductNameList() {
         ArrayList<String> productNameList = new ArrayList<>();
        for (Product product :productList) {
            productNameList.add(product.getProductName());
        }
        return productNameList;
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getUserUID() {
        return userUID;
    }
}