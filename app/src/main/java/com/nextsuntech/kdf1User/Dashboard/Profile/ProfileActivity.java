package com.nextsuntech.kdf1User.Dashboard.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nextsuntech.kdf1User.Order.OrderHistory.OrderHistoryActivity;
import com.nextsuntech.kdf1User.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout orderHistoryBT;
    ImageView profileIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        orderHistoryBT = findViewById(R.id.bt_profile_OrderHistory);
        profileIV = findViewById(R.id.iv_profile_back);



        orderHistoryBT.setOnClickListener(this);
        profileIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_profile_OrderHistory:
                Intent intent = new Intent(this, OrderHistoryActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.iv_profile_back:
                finish();
                break;
        }
    }
}