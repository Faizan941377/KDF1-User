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
    ProgressDialog progressDialog;
    OrderHistoryDetailAdapter orderHistoryDetailAdapter;
    List<GetOrderHistoryDetailDataModel> getOrderHistoryDetailDataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);

        backIV = findViewById(R.id.iv_orderHistoryDetail_back);
        orderHistoryDetailRV = findViewById(R.id.rv_orderHistoryDetail);

        progressDialog = new ProgressDialog(this);


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

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);


        orderHistoryDetailRV.setHasFixedSize(true);
        orderHistoryDetailRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        String  id = getIntent().getExtras().getString("CartAutoId");

        Log.d("cartAutoIdid", id);

        Call<GetOrderHistoryDetailResponse> call = RetrofitClient.getInstance().getApi().getOrderHistoryDetails(id);
        call.enqueue(new Callback<GetOrderHistoryDetailResponse>() {
            @Override
            public void onResponse(Call<GetOrderHistoryDetailResponse> call, Response<GetOrderHistoryDetailResponse> response) {

                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    getOrderHistoryDetailDataModels = response.body().getResult();
                    orderHistoryDetailRV.setAdapter(new OrderHistoryDetailAdapter(getApplicationContext(), getOrderHistoryDetailDataModels));
                    orderHistoryDetailAdapter = new OrderHistoryDetailAdapter(getApplicationContext(), getOrderHistoryDetailDataModels);
                    orderHistoryDetailRV.setAdapter(orderHistoryDetailAdapter);

                    Toast.makeText(OrderHistoryDetailActivity.this, getOrderHistoryDetailDataModels.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetOrderHistoryDetailResponse> call, Throwable t) {
                try {
                    Toast.makeText(OrderHistoryDetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}