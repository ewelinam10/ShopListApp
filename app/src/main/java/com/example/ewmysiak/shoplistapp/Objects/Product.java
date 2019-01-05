package com.example.ewmysiak.shoplistapp.Objects;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    public enum ProductCategory {
        FOOD,
        BOOK,
        CLOTHES;
    }

    private String productName;
    private String category;
    private int count;

    public Product() {
    }
    public Product(String productName, String category,int count) {
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
