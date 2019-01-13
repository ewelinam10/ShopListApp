package com.example.ewmysiak.shoplistapp.Objects;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Product implements Serializable {

    public enum ProductCategory implements Serializable {
        FOOD,
        BOOK,
        CLOTHES;
    }

    private String productName;
    private String category;
    private int count;

    public Product() {
    }

    public Product(String productName, String category, int count) {
        this.productName = productName;
        this.category = category;
        this.count = count;}

    public String getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }

    public String getProductName() {
        return productName;
    }

}
