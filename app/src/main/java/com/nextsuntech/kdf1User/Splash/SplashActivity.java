package com.nextsuntech.kdf1User.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.nextsuntech.kdf1User.Order.OrderHistory.OrderHistoryActivity;
import com.nextsuntech.kdf1User.Order.OrderHistoryDetail.OrderHistoryDetailActivity;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Users.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(com.nextsuntech.kdf1User.Splash.SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

   /* @Override
    protected void onStart() {
        super.onStart();

        if (SharePrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }*/
}