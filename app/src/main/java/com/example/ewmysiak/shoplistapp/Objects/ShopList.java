package com.example.ewmysiak.shoplistapp.Objects;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

@IgnoreExtraProperties
public class ShopList implements Serializable {
    public enum ShopListCategory implements Serializable {
        DAILY,
        WEEKLY,
        MONTHLY;
    }
    private String name;
    private String category;
    private String userUID;
    private ArrayList<Product> productList;
    private String shopListUID;

    public ShopList() {
    }
    public ShopList(String name, String category, ArrayList<Product> productList, String userUID) {
        this.name = name;
        this.category = category;
        this.productList = productList;
        this.userUID = userUID;
        this.shopListUID = shopListUID;

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

    public String getShopListUID() {
        return shopListUID;
    }

    public void setShopListUID(String shopListUID) {
        this.shopListUID = shopListUID;
    }

}