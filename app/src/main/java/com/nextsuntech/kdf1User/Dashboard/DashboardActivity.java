package com.nextsuntech.kdf1User.Dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nextsuntech.kdf1User.Cart.AddToCartActivity;
import com.nextsuntech.kdf1User.Dashboard.Adapter.BeveragesAdapter;
import com.nextsuntech.kdf1User.Dashboard.Adapter.CategoriesAdapter;
import com.nextsuntech.kdf1User.Dashboard.Adapter.DealsAdapter;
import com.nextsuntech.kdf1User.Model.CategoriesDataModel;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.MenuResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;
import com.nextsuntech.kdf1User.Users.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    TextView userNameTV;
    DealsAdapter dealsAdapter;
    List<CategoriesDataModel> categoriesDataModelsList;
    FloatingActionButton cartBT;
    ImageView logoutIV;
    TextView itemCount;

   /* List<GetCartDataModel> getCartDataModelList;

    private int cartQuantity = 0;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        categoriesRV = findViewById(R.id.rv_categories);
        dealsRV = findViewById(R.id.rv_deals);
        beveragesRV = findViewById(R.id.rv_beverages);
        searchET = findViewById(R.id.et_dashboard_search);
        cartBT = findViewById(R.id.bt_dashboard_cartButton);
        userNameTV = findViewById(R.id.tv_dashboard_userName);
        logoutIV = findViewById(R.id.iv_logout);
        //  itemCount = findViewById(R.id.text_count);

        //SharedPreferences
        LoginDataModel loginDataModel = SharedPrefManager.getInstance(this).getSavedUsers();
        String userName = loginDataModel.getUserName();
        userNameTV.setText(userName);


        progressDialog = new ProgressDialog(this);


        cartBT.setOnClickListener(this);
        logoutIV.setOnClickListener(this);

        setCategoriesAdapter();
        setBeveragesAdapter();
        setDealsAdapter();

      /*  searchET.addTextChangedListener(new TextWatcher() {
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
        });*/


       /* int quantity = 0;
        for (GetCartDataModel getCartDataModel : getCartDataModelist) {
            quantity += getCartDataModel.getTotalQuantity();
        }

        cartQuantity = quantity;
        itemCount.setText(String.valueOf(cartQuantity));*/

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
        // categoriesRV.setLayoutManager(new GridLayoutManager(this, 2));
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
                    String currentDateAndTime = SimpleDateFormat.getDateTimeInstance().format(new Date());
                    Log.e("DateAndTime", currentDateAndTime);
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
                startActivity(new Intent(this, AddToCartActivity.class));
                break;

            case R.id.iv_logout:
                SharedPrefManager.getInstance(DashboardActivity.this).clear();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}