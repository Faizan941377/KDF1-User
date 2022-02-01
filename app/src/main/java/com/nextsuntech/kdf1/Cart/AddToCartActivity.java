package com.nextsuntech.kdf1.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.nextsuntech.kdf1.Cart.Adapter.AddToCartAdapter;
import com.nextsuntech.kdf1.R;

public class AddToCartActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView addToCartRV;
    ImageView backIV;
    AddToCartAdapter addToCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        addToCartRV = findViewById(R.id.rv_addToCart);
        backIV = findViewById(R.id.iv_AddToCart_back);



        backIV.setOnClickListener(this);

        setAddToCartAdapter();
    }

    private void setAddToCartAdapter() {
        addToCartAdapter = new AddToCartAdapter(this);
        addToCartRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        addToCartRV.setAdapter(addToCartAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_AddToCart_back:
                finish();
                break;
        }
    }
}