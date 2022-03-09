package com.nextsuntech.kdf1User.Order.OrderHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nextsuntech.kdf1User.Cart.Adapter.AddToCartAdapter;
import com.nextsuntech.kdf1User.Model.GetCartDataModel;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.Order.OrderHistory.Adapter.OrderHistoryAdapter;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.CheckOutResponse;
import com.nextsuntech.kdf1User.Response.GetCartResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView backIV;
    RecyclerView orderHistoryRV;
    ProgressDialog progressDialog;
    OrderHistoryAdapter orderHistoryAdapter;
    List<GetCartDataModel> getCartDataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        backIV = findViewById(R.id.iv_orderHistory_back);
        orderHistoryRV = findViewById(R.id.rv_orderHistory);


        progressDialog = new ProgressDialog(this);


        backIV.setOnClickListener(this);



        setAdapterOrderHistory();
    }

    private void setAdapterOrderHistory() {


        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        orderHistoryRV.setHasFixedSize(true);
        orderHistoryRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        LoginDataModel loginModel = SharedPrefManager.getInstance(this).getSavedUsers();

        int userId = Integer.parseInt(String.valueOf(loginModel.getId()));

        Call<GetCartResponse> call = RetrofitClient.getInstance().getApi().getAddToCart(userId);
        call.enqueue(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    getCartDataModelList = response.body().getCartDataModels();
                    orderHistoryAdapter = new OrderHistoryAdapter(getApplicationContext(), getCartDataModelList);
                    orderHistoryRV.setAdapter(orderHistoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(OrderHistoryActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_orderHistory_back:
                finish();
                break;
        }
    }
}