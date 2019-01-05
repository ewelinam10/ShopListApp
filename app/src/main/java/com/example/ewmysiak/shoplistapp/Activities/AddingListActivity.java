package com.example.ewmysiak.shoplistapp.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.ewmysiak.shoplistapp.Objects.Product;
import com.example.ewmysiak.shoplistapp.Objects.ShopList;
import com.example.ewmysiak.shoplistapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unstoppable.submitbuttonview.SubmitButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddingListActivity extends AppCompatActivity {
    @BindView(R.id.adding_list)
    LinearLayout addingList;
    @BindView(R.id.fab_book)
    FloatingActionButton fabBook;
    @BindView(R.id.fab_clothes)
    FloatingActionButton fabClothes;
    @BindView(R.id.fab_food)
    FloatingActionButton fabFood;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.submitbutton)
    SubmitButton submitbutton;

    private boolean isFABOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_list);
        ButterKnife.bind(this);
    }

   public void onFabMenuButtonClicked(View view){
       if(!isFABOpen){
           showFABMenu();
       }else{
           closeFABMenu();
       }
   }
    public void onAddFoodButtonClicked(View view){
        createEditTex(Product.ProductCategory.FOOD);
    }
    public void onAddClothesButtonClicked(View view){
        createEditTex(Product.ProductCategory.CLOTHES);
    }

    public void onAddBookButtonClicked(View view){
        createEditTex(Product.ProductCategory.BOOK);
    }
    public void onSubmitButtonClicked(View view) {
        submitbutton.doResult(true);
        submitbutton.setOnResultEndListener(new SubmitButton.OnResultEndListener() {
            @Override
            public void onResultEnd() {
                finish();
            }
        });
    }


    private void createEditTex(Product.ProductCategory productCategory){
        final EditText editText = new EditText(this);
        editText.setBackgroundColor(getResources().getColor(R.color.browser_actions_bg_grey));
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                editText.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        TextInputLayout textInputLayout = new TextInputLayout(this);
        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        textInputLayoutParams.setMargins(10,10,10,10);

        textInputLayout.setLayoutParams(textInputLayoutParams);
        textInputLayout.addView(editText, editTextParams);
        textInputLayout.setHint(productCategory.toString());
        textInputLayout.setTag(productCategory);


       addingList.addView(textInputLayout);
}
    private void showFABMenu(){
        isFABOpen=true;
        fabBook.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabClothes.animate().translationY(-getResources().getDimension(R.dimen.standard_120));
        fabFood.animate().translationY(-getResources().getDimension(R.dimen.standard_165));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabFood.animate().translationY(0);
        fabBook.animate().translationY(0);
        fabClothes.animate().translationY(0);
    }
    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            super.onBackPressed();
        }else{
            closeFABMenu();
        }
    }
/*
    private void apply() {
        ArrayList<Product> productList = new ArrayList<>();
        Product product = new Product("Woda", "Jedzenie", 2);
        productList.add(product);
        productList.add(product);

        ShopList shopList = new ShopList("Lita2", "Daily", productList,user.getUid());
        addProductToCloudDatabase(shopList);
    }

    private void addProductToCloudDatabase(ShopList shopList) {
        db.collection(ShopList).add(shopList).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("dupa", "DocumentSnapshot written with ID: " + documentReference.getId());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("dupa", "Error adding document", e);
                    }
                });
    }*/

}
