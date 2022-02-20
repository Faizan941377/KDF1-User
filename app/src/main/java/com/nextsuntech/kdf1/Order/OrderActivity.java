package com.nextsuntech.kdf1.Order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.BookingDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalItemsTV;
    TextView totalPriceTV;
    TextView cartAutoIdTV;
    ImageView backIV;
    EditText customerNameET;
    RelativeLayout orderConfirmBT;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        customerNameET = findViewById(R.id.et_order_customerName);
        totalItemsTV = findViewById(R.id.tv_order_totalItems);
        totalPriceTV = findViewById(R.id.tv_order_totalPrice);
        cartAutoIdTV = findViewById(R.id.tv_order_cartAutoId);
        orderConfirmBT = findViewById(R.id.bt_order_confirm);
        backIV = findViewById(R.id.iv_oder_back);

        progressDialog = new ProgressDialog(this);


        int totalItems = Integer.parseInt(getIntent().getExtras().getString("totalItems"));
        int totalPrice = Integer.parseInt(getIntent().getExtras().getString("totalPrice"));
        int cartAutoId = Integer.parseInt(getIntent().getExtras().getString("cartAutoId"));

        totalItemsTV.setText(String.valueOf(totalItems));
        totalPriceTV.setText(String.valueOf(totalPrice));
        cartAutoIdTV.setText(String.valueOf(cartAutoId));

        orderConfirmBT.setOnClickListener(this);
        backIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_order_confirm:
                confirmOder();
                break;

            case R.id.iv_oder_back:
                finish();
                break;
        }
    }

    private void confirmOder() {

        if (customerNameET.length() == 0) {
            customerNameET.setError("Please enter the Table No!");
        } else {

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        String customerName = customerNameET.getText().toString().trim();
        int totalItem = Integer.parseInt(totalItemsTV.getText().toString());
        int totalPrice = Integer.parseInt(totalPriceTV.getText().toString());
        int cartAutoId = Integer.parseInt(cartAutoIdTV.getText().toString());

        Call<BookingDetailsResponse> call = RetrofitClient.getInstance().getApi().bookingDetails(customerName, totalItem, totalPrice, cartAutoId);
        call.enqueue(new Callback<BookingDetailsResponse>() {
            @Override
            public void onResponse(Call<BookingDetailsResponse> call, Response<BookingDetailsResponse> response) {
                BookingDetailsResponse bookingDetailsResponse = response.body();
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(OrderActivity.this, bookingDetailsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    customerNameET.setText("");
                    totalItemsTV.setText("0.00");
                    totalPriceTV.setText("0.00");
                    cartAutoIdTV.setText("0.00");
                }
            }

            @Override
            public void onFailure(Call<BookingDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(OrderActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

            orderConfirmBT.setEnabled(true);
        }

    }
}