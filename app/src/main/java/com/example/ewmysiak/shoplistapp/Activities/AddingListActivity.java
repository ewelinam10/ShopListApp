package com.example.ewmysiak.shoplistapp.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.ewmysiak.shoplistapp.Helpers.DataBaseHelper;
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
    SubmitButton submitButton;
    @BindView(R.id.listName)
    TextInputLayout listName;

    private boolean isFABOpen = false;
    private DataBaseHelper dbHelper;
    private ArrayList<Product> productList;
    private ArrayList<TextInputLayout> textInputLayoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_list);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        dbHelper = new DataBaseHelper(this);
        productList = new ArrayList<>();
        textInputLayoutList = new ArrayList<>();
        listName.getEditText().setShowSoftInputOnFocus(false);

    }

    public void onSubmitButtonClicked(View view) {
        for (TextInputLayout textInputLayout : textInputLayoutList
             ) {
            String category = textInputLayout.getTag(R.id.TAG_CATEGORY).toString();
            String element = textInputLayout.getEditText().getText().toString();
            Product product =new Product(element,category,1);
            productList.add(product);
        }
        dbHelper.addShopList(productList,submitButton,listName.getEditText().getText().toString());
        submitButton.setOnResultEndListener(new SubmitButton.OnResultEndListener() {
            @Override
            public void onResultEnd() {
                finish();
            }
        });
    }


    private void createEditText(Product.ProductCategory productCategory){
        final EditText editText = new EditText(this);
        editText.setShowSoftInputOnFocus(false);
        editText.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.browser_actions_bg_grey));
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                editText.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
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
        textInputLayout.setTag(R.id.TAG_CATEGORY,productCategory);

       addingList.addView(textInputLayout);
       textInputLayoutList.add(textInputLayout);
}




    public void onFabMenuButtonClicked(View view){
        if(!isFABOpen){
            showFABMenu();
        }else{
            closeFABMenu();
        }
    }

    public void onAddFoodButtonClicked(View view){
        createEditText(Product.ProductCategory.FOOD);
    }

    public void onAddClothesButtonClicked(View view){
        createEditText(Product.ProductCategory.CLOTHES);
    }

    public void onAddBookButtonClicked(View view){
        createEditText(Product.ProductCategory.BOOK);
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabFood.animate().translationY(0);
        fabBook.animate().translationY(0);
        fabClothes.animate().translationY(0);
    }

    private void showFABMenu(){
        isFABOpen=true;
        fabBook.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabClothes.animate().translationY(-getResources().getDimension(R.dimen.standard_120));
        fabFood.animate().translationY(-getResources().getDimension(R.dimen.standard_165));
    }

    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            super.onBackPressed();
        }else{
            closeFABMenu();
        }
    }
}
