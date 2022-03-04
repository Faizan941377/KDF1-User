package com.nextsuntech.kdf1User.ProductDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.AddToCartResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView descriptionTV;
    TextView productTitleTV;
    TextView priceTV;
    CircleImageView productDetailsIV;
    ProgressDialog progressDialog;
    ImageView backIV;
    LinearLayout addToCartBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        descriptionTV = findViewById(R.id.tv_description);
        productTitleTV = findViewById(R.id.tv_productDetails_title);
        productDetailsIV = findViewById(R.id.iv_productDetails);
        backIV = findViewById(R.id.iv_productDetails_back);
        addToCartBT = findViewById(R.id.bt_product_details_addToCart);
        priceTV = findViewById(R.id.tv_productDetails_price);
        progressDialog = new ProgressDialog(this);



        descriptionTV.setText(getIntent().getExtras().getString("description"));
        productTitleTV.setText(getIntent().getExtras().getString("productTitle"));
        priceTV.setText(getIntent().getExtras().getString("price"));
        Intent i = getIntent();
        String image = i.getStringExtra("img_url");
        Glide.with(this).load(image).into(productDetailsIV);


        backIV.setOnClickListener(this);
        addToCartBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_productDetails_back:
                finish();
                break;

            case R.id.bt_product_details_addToCart:
                addToCart();
                break;
        }
    }

    LoginDataModel loginDataModel = SharedPrefManager.getInstance(this).getSavedUsers();

    private void addToCart() {

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        String  productId = getIntent().getExtras().getString("productId");
        String userId = String.valueOf(loginDataModel.getId());
        String qty = "1";
        String price = getIntent().getExtras().getString("price");

        Call<AddToCartResponse> call = RetrofitClient.getInstance().getApi().AddToCart(productId, Integer.parseInt(userId),qty,price);
        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                AddToCartResponse addToCartResponse = response.body();
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), addToCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}