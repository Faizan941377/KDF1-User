package com.nextsuntech.kdf1.CustomerOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nextsuntech.kdf1.R;

public class CustomerOrderActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout makeBookingBT;
    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);




        makeBookingBT = findViewById(R.id.bt_customerOrder_Booking);
        backIV = findViewById(R.id.iv_orderConfirm_back);



        makeBookingBT.setOnClickListener(this);
        backIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_customerOrder_Booking:
                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_orderConfirm_back:
                finish();
                break;
        }
    }
}