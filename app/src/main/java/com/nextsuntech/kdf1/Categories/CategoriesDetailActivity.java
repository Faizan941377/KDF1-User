package com.nextsuntech.kdf1.Categories;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nextsuntech.kdf1.Categories.Adapter.CategoriesDetailsAdapter;
import com.nextsuntech.kdf1.Model.GetProductDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.GetProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoriesDetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchET;
    RecyclerView categoriesDetailRV;
    List<GetProductDataModel> productDataModelList;
    CategoriesDetailsAdapter categoriesDetailsAdapter;
    ProgressDialog progressDialog;
    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_detail);


        categoriesDetailRV = findViewById(R.id.rv_categoriesDetails);
        searchET = findViewById(R.id.et_rowCategoryDetail);
        backIV = findViewById(R.id.iv_categoryDetails_back);

        backIV.setOnClickListener(this);




        progressDialog = new ProgressDialog(this);

        setCategoriesDetailsAdapter();

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                categoriesDetailsAdapter.getFilter().filter(s);
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String userId = intent.getStringExtra("userId");
            String productId = intent.getStringExtra("productId");
            String qty = intent.getStringExtra("quantity");
            String price = intent.getStringExtra("price");

            Log.e("userId",userId);
            Log.e("productId",productId);
            Log.e("qty",qty);
            Log.e("price",price);
        }
    };

    private void setCategoriesDetailsAdapter() {

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        categoriesDetailRV.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        categoriesDetailRV.setLayoutManager(staggeredGridLayoutManager);

        String id = getIntent().getExtras().getString("id");

        Call<GetProductResponse> call = RetrofitClient.getInstance().getApi().getProductResponse(id);
        call.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                if (response.isSuccessful()){
                    productDataModelList = response.body().getProductDataModelList();
                    categoriesDetailRV.setAdapter(new CategoriesDetailsAdapter(getApplicationContext(), productDataModelList));
                    categoriesDetailsAdapter = new CategoriesDetailsAdapter(getApplicationContext(), productDataModelList);
                    categoriesDetailRV.setAdapter(categoriesDetailsAdapter);
                    progressDialog.dismiss();
                }else {
                    Toast.makeText(CategoriesDetailActivity.this,response.message(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(CategoriesDetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_categoryDetails_back:
                finish();
                break;
        }
    }
}