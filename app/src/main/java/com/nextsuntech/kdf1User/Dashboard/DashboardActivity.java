package com.nextsuntech.kdf1User.Dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nextsuntech.kdf1User.Cart.AddToCartActivity;
import com.nextsuntech.kdf1User.Dashboard.Adapter.BeveragesAdapter;
import com.nextsuntech.kdf1User.Dashboard.Adapter.CategoriesAdapter;
import com.nextsuntech.kdf1User.Dashboard.Adapter.DealsAdapter;
import com.nextsuntech.kdf1User.Dashboard.Adapter.SliderAdapter;
import com.nextsuntech.kdf1User.Dashboard.Profile.ProfileActivity;
import com.nextsuntech.kdf1User.Model.CategoriesDataModel;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Model.SliderDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.Order.OrderHistory.OrderHistoryActivity;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.MenuResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;
import com.nextsuntech.kdf1User.Users.LoginActivity;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    RecyclerView categoriesRV;
    RecyclerView beveragesRV;
    CategoriesAdapter categoriesAdapter;
    BeveragesAdapter beveragesAdapter;
    RecyclerView dealsRV;
    EditText searchET;
    TextView userNameTV;
    DealsAdapter dealsAdapter;
    List<CategoriesDataModel> categoriesDataModelsList;
    FloatingActionButton cartBT;
    ImageView logoutIV;
    ImageView profileIV;
    TextView itemCount;

   /* List<GetCartDataModel> getCartDataModelList;

    private int cartQuantity = 0;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        categoriesRV = findViewById(R.id.rv_categories);
       // dealsRV = findViewById(R.id.rv_deals);
       // beveragesRV = findViewById(R.id.rv_beverages);
        searchET = findViewById(R.id.et_dashboard_search);
        cartBT = findViewById(R.id.bt_dashboard_cartButton);
        userNameTV = findViewById(R.id.tv_dashboard_userName);
        logoutIV = findViewById(R.id.iv_logout);
        profileIV = findViewById(R.id.iv_dashboard_profile);
        //  itemCount = findViewById(R.id.text_count);

        //SharedPreferences
        LoginDataModel loginDataModel = SharedPrefManager.getInstance(this).getSavedUsers();
        String userName = loginDataModel.getUserName();
        userNameTV.setText(userName);


        progressDialog = new ProgressDialog(this);


        cartBT.setOnClickListener(this);
        logoutIV.setOnClickListener(this);
        profileIV.setOnClickListener(this);

        setCategoriesAdapter();
      //  setBeveragesAdapter();
     //   setDealsAdapter();

      /*  searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                categoriesAdapter.getFilter().filter(s);
            }
        });*/


       /* int quantity = 0;
        for (GetCartDataModel getCartDataModel : getCartDataModelist) {
            quantity += getCartDataModel.getTotalQuantity();
        }

        cartQuantity = quantity;
        itemCount.setText(String.valueOf(cartQuantity));*/


        sliderImages();

    }

    private void sliderImages() {

        // Urls of our images.
        String url1 = "https://lh5.googleusercontent.com/p/AF1QipPD5CxRiTyKxs_qfaA4nHzgrRRBIBxTRY6SeMqj";
        String url2 = "https://i0.wp.com/www.explorewithjeph.com/wp-content/uploads/2020/06/Arabic-Mixed-Grill-Platter.jpg?resize=640%2C400&ssl=1";
        String url3 = "https://media-cdn.tripadvisor.com/media/photo-s/1a/11/c1/d5/1-kg-mix-grill-it-comes.jpg";
        String url4 = "https://kdfrestaurant.com/website-static/images/bg/13.jpg";
        // we are creating array list for storing our image urls.
        ArrayList<SliderDataModel> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderDataModel(url1));
        sliderDataArrayList.add(new SliderDataModel(url2));
        sliderDataArrayList.add(new SliderDataModel(url3));
        sliderDataArrayList.add(new SliderDataModel(url4));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();
    }


    private void setBeveragesAdapter() {
        beveragesAdapter = new BeveragesAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        beveragesRV.setLayoutManager(gridLayoutManager);
        beveragesRV.setAdapter(beveragesAdapter);
    }

    private void setDealsAdapter() {
        dealsAdapter = new DealsAdapter(this);
        dealsRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        dealsRV.setAdapter(dealsAdapter);
    }

    private void setCategoriesAdapter() {

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        categoriesRV.setHasFixedSize(true);
        /*categoriesRV.setLayoutManager(new GridLayoutManager(this, 2));
        categoriesRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));*/
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        categoriesRV.setLayoutManager(staggeredGridLayoutManager);

        Call<MenuResponse> call = RetrofitClient.getInstance().getApi().menuResponse();
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if (response.isSuccessful()) {
                    categoriesDataModelsList = response.body().getResult();
                    categoriesRV.setAdapter(new CategoriesAdapter(getApplicationContext(), categoriesDataModelsList));
                    categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categoriesDataModelsList);
                    categoriesRV.setAdapter(categoriesAdapter);
                    progressDialog.dismiss();
                    String currentDateAndTime = SimpleDateFormat.getDateTimeInstance().format(new Date());
                    Log.e("DateAndTime", currentDateAndTime);
                } else {
                    Toast.makeText(DashboardActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_dashboard_cartButton:
                startActivity(new Intent(this, AddToCartActivity.class));
                break;

            case R.id.iv_logout:
                SharedPrefManager.getInstance(DashboardActivity.this).clear();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_dashboard_profile:
                Intent intent1 = new Intent(this, ProfileActivity.class);
                startActivity(intent1);
                break;
        }
    }
}