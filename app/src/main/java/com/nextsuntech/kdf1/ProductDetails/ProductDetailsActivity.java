package com.nextsuntech.kdf1.ProductDetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nextsuntech.kdf1.Model.GetProductDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.GetProductResponse;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView descriptionTV;
    TextView productTitleTV;
    CircleImageView productDetailsIV;
    ImageView backIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        descriptionTV = findViewById(R.id.tv_description);
        productTitleTV = findViewById(R.id.tv_productDetails_title);
        productDetailsIV = findViewById(R.id.iv_productDetails);
        backIV = findViewById(R.id.iv_productDetails_back);



        descriptionTV.setText(getIntent().getExtras().getString("description"));
        productTitleTV.setText(getIntent().getExtras().getString("productTitle"));
        Intent i = getIntent();
        String image = i.getStringExtra("img_url");
        Glide.with(this).load(image).into(productDetailsIV);


        backIV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_productDetails_back:
                finish();
                break;
        }
    }
}