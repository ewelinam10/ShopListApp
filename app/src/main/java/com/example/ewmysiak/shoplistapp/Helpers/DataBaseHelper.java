package com.example.ewmysiak.shoplistapp.Helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataBaseHelper {

    private FirebaseFirestore db;
    private FirebaseUser user;

    public DataBaseHelper() {
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }
}
