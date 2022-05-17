package com.nextsuntech.kdf1User.Users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nextsuntech.kdf1User.Dashboard.DashboardActivity;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.LoginResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signUpTV;
    EditText emailET;
    EditText passwordET;
    RelativeLayout loginBT;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpTV = findViewById(R.id.tv_login_signUp);
        emailET = findViewById(R.id.et_login_email);
        passwordET = findViewById(R.id.et_login_password);
        loginBT = findViewById(R.id.bt_login);

        progressDialog = new ProgressDialog(this);


        loginBT.setOnClickListener(this);
        signUpTV.setOnClickListener(this);
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

            case R.id.tv_login_signUp:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
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
                    try {
                        if (response.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            SharedPrefManager.getInstance(LoginActivity.this).saveLoginUser(loginResponse.getResult());
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "System is busy", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this,loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
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