package com.example.ewmysiak.shoplistapp.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ewmysiak.shoplistapp.Objects.Product;
import com.example.ewmysiak.shoplistapp.Objects.ShopList;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.unstoppable.submitbuttonview.SubmitButton;

import java.util.ArrayList;

public class DataBaseHelper {

    private FirebaseFirestore fb;
    private FirebaseUser FBuser;
    private static String USER_UUID = "userUID";
    private static String SHOP_LIST_COLLECTION= "ShopList";
    private static String USER_DATA = "UserData";
    private static String USER_NAME = "UserName";
    private static String PRODUCT_COUNT = "ProductCount";

    private TinyDB tinyDB;
    private Context context;


    public DataBaseHelper(Context context) {
        fb = FirebaseFirestore.getInstance();
        FBuser = FirebaseAuth.getInstance().getCurrentUser();
        tinyDB = new TinyDB(context);
    }
    public void setUserDatainLocalDB(){
        tinyDB.putString(USER_NAME,FBuser.getDisplayName());
        tinyDB.putString(USER_UUID,FBuser.getUid());
    }
    public void setProductCountInLocalDataBase(int productCount){
        tinyDB.putInt(PRODUCT_COUNT,productCount);
    }
    public int getProductCountInLocalDataBase(){
        return tinyDB.getInt(PRODUCT_COUNT);
    }
    public FirestoreRecyclerOptions<ShopList> getLists(){
        Query query = fb.collection(SHOP_LIST_COLLECTION).whereEqualTo(USER_UUID,FBuser.getUid());

        FirestoreRecyclerOptions<ShopList> response = new FirestoreRecyclerOptions.Builder<ShopList>()
                .setQuery(query, ShopList.class)
                .build();
        return response;
    }

    public boolean addShopList(ArrayList<Product> productList, final SubmitButton submitButton, String listName){
        ShopList shopList = new ShopList(listName, "Daily", productList,FBuser.getUid());
        shopList.setShopListUID(FBuser.getUid() + "__NUM.__" + this.getProductCountInLocalDataBase());
        fb.collection(SHOP_LIST_COLLECTION).document(shopList.getShopListUID()).set(shopList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(this.toString(), "Shop List with name : added correctly");
                submitButton.doResult(true);
            }
        }) .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(this.toString(), "Error adding Shop List", e);
                submitButton.doResult(false);
            }
        });
        return true;
    }

    public void deleteProductFromList(String shopListUUID){
        fb.collection(SHOP_LIST_COLLECTION).document(shopListUUID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(this.toString(), "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(this.toString(), "Error deleting document", e);
                    }
                });
    }

}
