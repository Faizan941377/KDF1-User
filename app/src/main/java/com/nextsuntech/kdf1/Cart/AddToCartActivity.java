package com.nextsuntech.kdf1.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nextsuntech.kdf1.Cart.Adapter.AddToCartAdapter;
import com.nextsuntech.kdf1.Dashboard.Adapter.CategoriesAdapter;
import com.nextsuntech.kdf1.Model.GetCartDataModel;
import com.nextsuntech.kdf1.Model.LoginDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.AddToCartResponse;
import com.nextsuntech.kdf1.Response.GetCartResponse;
import com.nextsuntech.kdf1.SharedPref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    RecyclerView addToCartRV;
    ImageView backIV;
    AddToCartAdapter addToCartAdapter;
    List<GetCartDataModel> getCartDataModelList;
    TextView cartTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);


        addToCartRV = findViewById(R.id.rv_addToCart);
        backIV = findViewById(R.id.iv_AddToCart_back);
        cartTV = findViewById(R.id.tv_rowCategories_details_heading);
        progressDialog = new ProgressDialog(this);


        backIV.setOnClickListener(this);
        cartTV.setOnClickListener(this);
        setAddToCartAdapter();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_AddToCart_back:
                finish();
                break;
        }
    }

    private void setAddToCartAdapter() {

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        addToCartRV.setHasFixedSize(true);
        addToCartRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        LoginDataModel loginModel = SharedPrefManager.getInstance(this).getSavedUsers();
        int userId = Integer.parseInt(String.valueOf(loginModel.getId()));

        Call<GetCartResponse> call = RetrofitClient.getInstance().getApi().getAddToCart(userId);
        call.enqueue(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    getCartDataModelList = response.body().getCartDataModels();
                    // addToCartRV.setAdapter(new CategoriesAdapter(getApplicationContext(), getCartDataModelList));
                    addToCartAdapter = new AddToCartAdapter(getApplicationContext(), getCartDataModelList);
                    addToCartRV.setAdapter(addToCartAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(AddToCartActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}