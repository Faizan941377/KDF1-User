package com.nextsuntech.kdf1User.Order.OrderHistoryDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nextsuntech.kdf1User.Categories.Adapter.CategoriesDetailsAdapter;
import com.nextsuntech.kdf1User.Model.GetOrderHistoryDetailDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.Order.OrderHistoryDetail.Adapter.OrderHistoryDetailAdapter;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.GetOrderHistoryDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backIV;
    RecyclerView orderHistoryDetailRV;
    OrderHistoryDetailAdapter orderHistoryDetailAdapter;
    List<GetOrderHistoryDetailDataModel> getOrderHistoryDetailDataModels;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);

        backIV = findViewById(R.id.iv_orderHistoryDetail_back);
        orderHistoryDetailRV = findViewById(R.id.rv_orderHistoryDetail);
        shimmerFrameLayout = findViewById(R.id.order_history_detail_shimmer);

        shimmerFrameLayout.startShimmer();


        backIV.setOnClickListener(this);

        setAdapter();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_orderHistoryDetail_back:
                finish();
                break;
        }
    }

    private void setAdapter() {

        orderHistoryDetailRV.setHasFixedSize(true);
        orderHistoryDetailRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

         String  CartAutoId = getIntent().getExtras().getString("CartAutoId");
         Log.d("dataFromServer", CartAutoId);

        Call<GetOrderHistoryDetailResponse> call = RetrofitClient.getInstance().getApi().getOrderHistoryDetails(Integer.valueOf(CartAutoId));
        call.enqueue(new Callback<GetOrderHistoryDetailResponse>() {
            @Override
            public void onResponse(Call<GetOrderHistoryDetailResponse> call, Response<GetOrderHistoryDetailResponse> response) {
                if (response.isSuccessful()) {
                    shimmerFrameLayout.stopShimmer();
                    orderHistoryDetailRV.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.setVisibility(View.GONE);
                    getOrderHistoryDetailDataModels = response.body().getOrderHistoryDetailDataModels();
                    orderHistoryDetailAdapter = new OrderHistoryDetailAdapter(getApplicationContext(),getOrderHistoryDetailDataModels);
                    orderHistoryDetailRV.setAdapter(orderHistoryDetailAdapter);
                }else {
                    Toast.makeText(OrderHistoryDetailActivity.this, "Please check your Internet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetOrderHistoryDetailResponse> call, Throwable t) {
                try {
                    Toast.makeText(OrderHistoryDetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}