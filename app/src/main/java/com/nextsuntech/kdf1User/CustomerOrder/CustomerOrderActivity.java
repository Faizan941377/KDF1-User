package com.nextsuntech.kdf1User.CustomerOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.CheckOutResponse;
import com.nextsuntech.kdf1User.Response.CustomerBookingDetailsResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerOrderActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout makeBookingBT;
    ImageView backIV;
    EditText phoneET;
    EditText emailET;
    EditText cityET;
    EditText addressEt;
    TextView totalItemsTV;
    TextView totalPriceTV;
    TextView cartAutoIdTV;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);


        makeBookingBT = findViewById(R.id.bt_customerOrder_Booking);
        backIV = findViewById(R.id.iv_orderConfirm_back);
        phoneET = findViewById(R.id.et_customerOder_Phone);
        emailET = findViewById(R.id.et_customerOder_email);
        cityET = findViewById(R.id.et_customerOder_city);
        addressEt = findViewById(R.id.et_customerOder_address);
        totalItemsTV = findViewById(R.id.tv_CustomerOrder_totalItems);
        totalPriceTV = findViewById(R.id.tv_customerOrder_totalPrice);
        cartAutoIdTV = findViewById(R.id.tv_customerOrder_cartAutoId);

        progressDialog = new ProgressDialog(this);


        int totalItems = Integer.parseInt(getIntent().getExtras().getString("totalItems"));
        int totalPrice = Integer.parseInt(getIntent().getExtras().getString("totalPrice"));
//        int cartAutoId = Integer.parseInt(getIntent().getExtras().getString("cartAutoId"));


        totalItemsTV.setText(String.valueOf(totalItems));
        totalPriceTV.setText(String.valueOf(totalPrice));

        LoginDataModel loginDataModel = SharedPrefManager.getInstance(this).getSavedUsers();
        emailET.setText(loginDataModel.getEmail());
        phoneET.setText(loginDataModel.getMobileNo());
        cityET.setText("Peshawar");
        addressEt.setText(loginDataModel.getAddress());

        makeBookingBT.setOnClickListener(this);
        backIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_customerOrder_Booking:
                CustomerOder();
                break;

            case R.id.iv_orderConfirm_back:
                finish();
                break;
        }
    }

    private void CustomerOder() {

        String phone = phoneET.getText().toString().trim();
        String email = emailET.getText().toString();
        String city = cityET.getText().toString().trim();
        String address = addressEt.getText().toString().trim();

        String currentDateAndTime = SimpleDateFormat.getDateTimeInstance().format(new Date());
        Log.e("DateAndTime", currentDateAndTime);

        if (phoneET.length() == 0) {
            phoneET.setError("Please enter your phone number");
        } else if (cityET.length() == 0) {
            cityET.setError("Please enter your city name");
        } else if (addressEt.length() == 0) {
            addressEt.setError("Please enter your receiving order address");
        } else {

            progressDialog.show();
            progressDialog.setMessage("Order Confirming...");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);

            int totalItem = Integer.parseInt(totalItemsTV.getText().toString());
            int totalPrice = Integer.parseInt(totalPriceTV.getText().toString());

            String jsonObject = getIntent().getExtras().getString("jsonObject");

            Log.d("arrayList", jsonObject);

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            Log.d("cardJason", requestBody.toString());


            Call<CheckOutResponse> call1 = RetrofitClient.getInstance().getApi().postOrder(requestBody);
            call1.enqueue(new Callback<CheckOutResponse>() {
                @Override
                public void onResponse(Call<CheckOutResponse> call, Response<CheckOutResponse> response) {
                    CheckOutResponse checkOutResponse = response.body();
                    if (response.isSuccessful()) {
                        String cartAutoID = String.valueOf(checkOutResponse.getAutoId());

                        Call<CustomerBookingDetailsResponse> call2 = RetrofitClient.getInstance().getApi().customerBookingDetails(phone,
                                totalItem, totalPrice, Integer.parseInt(cartAutoID), currentDateAndTime, email, city, address);
                        call2.enqueue(new Callback<CustomerBookingDetailsResponse>() {
                            @Override
                            public void onResponse(Call<CustomerBookingDetailsResponse> call, Response<CustomerBookingDetailsResponse> response) {
                                CustomerBookingDetailsResponse customerBookingDetailsResponse = response.body();
                                if (response.isSuccessful()) {
                                    progressDialog.dismiss();
                                    cartAutoIdTV.setText(cartAutoID);
                                    Toast.makeText(CustomerOrderActivity.this, customerBookingDetailsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CustomerOrderActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<CustomerBookingDetailsResponse> call, Throwable t) {
                                try {
                                    progressDialog.dismiss();
                                    Toast.makeText(CustomerOrderActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<CheckOutResponse> call, Throwable t) {
                    try {
                        Toast.makeText(CustomerOrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }

    }
}