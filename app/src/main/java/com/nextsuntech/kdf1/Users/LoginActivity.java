package com.nextsuntech.kdf1.Users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nextsuntech.kdf1.Dashboard.DashboardActivity;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.LoginResponse;
import com.nextsuntech.kdf1.SharedPref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailET;
    EditText passwordET;
    RelativeLayout loginBT;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.et_login_email);
        passwordET = findViewById(R.id.et_login_password);
        loginBT = findViewById(R.id.bt_login);

        progressDialog = new ProgressDialog(this);

        emailET.setText("fayasz22@gmail.com");
        emailET.setEnabled(true);
        passwordET.setText("12345");
        passwordET.setEnabled(true);


        loginBT.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_login:
                Login();
                break;
        }
    }

    private void Login() {

        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if (emailET.length() == 0) {
            emailET.setError("Please Enter Your Email Address");
            emailET.requestFocus();
            return;
        } else if (passwordET.length() == 0) {
            passwordET.setError("Please Enter Your Password");
            passwordET.requestFocus();
            return;
        } else if (passwordET.length() < 5) {
            passwordET.setError("Password should be at least 6 characters long");
            passwordET.requestFocus();
            return;
        } else {

            progressDialog.show();
            progressDialog.setMessage("Please wait it will take few moments");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);

            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().loginResponse(email, password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        SharedPrefManager.getInstance(LoginActivity.this).saveLoginUser(loginResponse.getLoginDataModel());
                        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, loginResponse.getLoginModel(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    try {
                        Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }
    }
}