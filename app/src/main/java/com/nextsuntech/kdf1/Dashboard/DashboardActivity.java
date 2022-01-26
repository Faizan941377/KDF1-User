package com.nextsuntech.kdf1.Dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class DashboardActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;
    RecyclerView categoriesRV;
    CategoriesAdapter categoriesAdapter;
    RecyclerView dealsRV;
    EditText searchET;
    DealsAdapter dealsAdapter;
    private List<CategoriesDataModel> categoriesDetailDataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        categoriesRV = findViewById(R.id.rv_categories);
        dealsRV = findViewById(R.id.rv_deals);
        searchET = findViewById(R.id.et_dashboard_search);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        progressDialog = new ProgressDialog(this);


        setCategoriesAdapter();
        setDealsAdapter();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setCategoriesAdapter();
                setDealsAdapter();
                searchET.setText("");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                categoriesAdapter.getFilter().filter(s);
            }
        });
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
        // categoriesRV.setLayoutManager(new GridLayoutManager(this, 2));
        //categoriesRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        categoriesRV.setLayoutManager(staggeredGridLayoutManager);

        Call<MenuResponse> call = RetrofitClient.getInstance().getApi().menuResponse();
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if (response.isSuccessful()) {
                    categoriesDetailDataModels = response.body().getResult();
                    categoriesRV.setAdapter(new CategoriesAdapter(getApplicationContext(), categoriesDetailDataModels));
                    categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categoriesDetailDataModels);
                    categoriesRV.setAdapter(categoriesAdapter);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(DashboardActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                }
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
}