package com.nextsuntech.kdf1User.Dashboard.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Order.OrderHistory.OrderHistoryActivity;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;
import com.nextsuntech.kdf1User.Users.LoginActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {



    RelativeLayout orderHistoryBT;
    ImageView profileIV;
    TextView waiterNameTV;
    TextView typeTV;
    TextView emailTV;
    TextView statusTV;
    TextView userCreateAtTV;
    TextView logoutTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        orderHistoryBT = findViewById(R.id.bt_profile_OrderHistory);
        profileIV = findViewById(R.id.iv_profile_back);
        waiterNameTV = findViewById(R.id.tv_profile_waiterName);
        typeTV = findViewById(R.id.tv_profile_type);
        emailTV = findViewById(R.id.tv_profile_email);
        statusTV = findViewById(R.id.tv_profile_status);
        userCreateAtTV = findViewById(R.id.tv_profile_userCreateDate);
        logoutTV = findViewById(R.id.tv_profile_logout);

        LoginDataModel userDetails = SharedPrefManager.getInstance(this).getSavedUsers();
        waiterNameTV.setText(userDetails.getUserName());
        typeTV.setText(userDetails.getType());
        emailTV.setText(userDetails.getEmail());
        statusTV.setText(userDetails.getStatus());
        userCreateAtTV.setText(userDetails.getCreateAt());


        logoutTV.setOnClickListener(this);
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

            case R.id.tv_profile_logout:
                SharedPrefManager.getInstance(ProfileActivity.this).clear();
                Intent intent1 = new Intent(ProfileActivity.this, LoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                finish();
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}