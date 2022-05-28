package com.nextsuntech.kdf1User.Order.OrderHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nextsuntech.kdf1User.Cart.Adapter.AddToCartAdapter;
import com.nextsuntech.kdf1User.Dashboard.Adapter.CategoriesAdapter;
import com.nextsuntech.kdf1User.Model.GetCartDataModel;
import com.nextsuntech.kdf1User.Model.GetOrderHistoryDataModel;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.Order.OrderHistory.Adapter.OrderHistoryAdapter;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.CheckOutResponse;
import com.nextsuntech.kdf1User.Response.GetCartResponse;
import com.nextsuntech.kdf1User.Response.GetOrderHistoryResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView backIV;
    RecyclerView orderHistoryRV;
    OrderHistoryAdapter orderHistoryAdapter;
    List<GetOrderHistoryDataModel> getOrderHistoryDataModelList;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        backIV = findViewById(R.id.iv_orderHistory_back);
        orderHistoryRV = findViewById(R.id.rv_orderHistory);
        shimmerFrameLayout = findViewById(R.id.order_history_shimmer);

        shimmerFrameLayout.startShimmer();


        backIV.setOnClickListener(this);



        setAdapterOrderHistory();
    }

    private void setAdapterOrderHistory() {

        orderHistoryRV.setHasFixedSize(true);
        orderHistoryRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

       Call<GetOrderHistoryResponse> call = RetrofitClient.getInstance().getApi().getOrderHistory();
       call.enqueue(new Callback<GetOrderHistoryResponse>() {
           @Override
           public void onResponse(Call<GetOrderHistoryResponse> call, Response<GetOrderHistoryResponse> response) {
               if (response.isSuccessful()){
                   shimmerFrameLayout.stopShimmer();
                   orderHistoryRV.setVisibility(View.VISIBLE);
                   shimmerFrameLayout.setVisibility(View.GONE);
                   getOrderHistoryDataModelList = response.body().getGetOrderHistoryDataModelList();
                   orderHistoryRV.setAdapter(new OrderHistoryAdapter(getApplicationContext(), getOrderHistoryDataModelList));
                   orderHistoryAdapter = new OrderHistoryAdapter(getApplicationContext(), getOrderHistoryDataModelList);
                   orderHistoryRV.setAdapter(orderHistoryAdapter);
               }else {
                   Toast.makeText(OrderHistoryActivity.this, "Please check your internet", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<GetOrderHistoryResponse> call, Throwable t) {
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