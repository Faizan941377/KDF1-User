package com.nextsuntech.kdf1.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nextsuntech.kdf1.Cart.Adapter.AddToCartAdapter;
import com.nextsuntech.kdf1.Dashboard.Adapter.CategoriesAdapter;
import com.nextsuntech.kdf1.Model.GetCartDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.AddToCartResponse;
import com.nextsuntech.kdf1.Response.GetCartResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartActivity extends AppCompatActivity implements View.OnClickListener {


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

    }
}