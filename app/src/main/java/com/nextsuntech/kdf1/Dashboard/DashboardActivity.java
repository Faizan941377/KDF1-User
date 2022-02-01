package com.nextsuntech.kdf1.Dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nextsuntech.kdf1.Cart.AddToCartActivity;
import com.nextsuntech.kdf1.Dashboard.Adapter.BeveragesAdapter;
import com.nextsuntech.kdf1.Dashboard.Adapter.CategoriesAdapter;
import com.nextsuntech.kdf1.Dashboard.Adapter.DealsAdapter;
import com.nextsuntech.kdf1.Model.CategoriesDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.MenuResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    RecyclerView categoriesRV;
    RecyclerView beveragesRV;
    CategoriesAdapter categoriesAdapter;
    BeveragesAdapter beveragesAdapter;
    RecyclerView dealsRV;
    EditText searchET;
    DealsAdapter dealsAdapter;
    List<CategoriesDataModel> categoriesDataModelsList;
    FloatingActionButton cartBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        categoriesRV = findViewById(R.id.rv_categories);
        dealsRV = findViewById(R.id.rv_deals);
        beveragesRV = findViewById(R.id.rv_beverages);
        searchET = findViewById(R.id.et_dashboard_search);
        cartBT = findViewById(R.id.bt_dashboard_cartButton);


        progressDialog = new ProgressDialog(this);


        cartBT.setOnClickListener(this);

        setCategoriesAdapter();
        setBeveragesAdapter();
        setDealsAdapter();

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                categoriesAdapter.getFilter().filter(s);
            }
        });
    }

    private void setBeveragesAdapter() {
        beveragesAdapter = new BeveragesAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        beveragesRV.setLayoutManager(gridLayoutManager);
        beveragesRV.setAdapter(beveragesAdapter);
    }

    private void setDealsAdapter() {
        dealsAdapter = new DealsAdapter(this);
        dealsRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        dealsRV.setAdapter(dealsAdapter);
    }

    private void setCategoriesAdapter() {

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        categoriesRV.setHasFixedSize(true);
        categoriesRV.setLayoutManager(new GridLayoutManager(this, 2));
        categoriesRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
       /* StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        categoriesRV.setLayoutManager(staggeredGridLayoutManager);*/

        Call<MenuResponse> call = RetrofitClient.getInstance().getApi().menuResponse();
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if (response.isSuccessful()) {
                    categoriesDataModelsList = response.body().getResult();
                    categoriesRV.setAdapter(new CategoriesAdapter(getApplicationContext(), categoriesDataModelsList));
                    categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categoriesDataModelsList);
                    categoriesRV.setAdapter(categoriesAdapter);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(DashboardActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_dashboard_cartButton:
                Intent intent = new Intent(this, AddToCartActivity.class);
                startActivity(intent);
                break;
        }
    }
}