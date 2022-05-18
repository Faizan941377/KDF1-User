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

import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView loginTV;
    EditText nameET;
    EditText emailET;
    EditText firstNameET;
    EditText lastNameET;
    EditText contactET;
    EditText passwordET;
    EditText addressET;
    RelativeLayout registerBT;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginTV = findViewById(R.id.tv_register_login);
        nameET = findViewById(R.id.et_register_name);
        emailET = findViewById(R.id.et_register_email);
        firstNameET = findViewById(R.id.et_register_firstName);
        lastNameET = findViewById(R.id.et_register_lastName);
        contactET = findViewById(R.id.et_register_contact);
        addressET = findViewById(R.id.et_register_address);
        passwordET = findViewById(R.id.et_register_password);
        registerBT = findViewById(R.id.bt_register);


        progressDialog = new ProgressDialog(this);


        registerBT.setOnClickListener(this);
        loginTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_register:
                Register();
                break;

            case R.id.tv_register_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void Register() {

        String userName = nameET.getText().toString();
        String email = emailET.getText().toString();
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String contact = contactET.getText().toString();
        String address = addressET.getText().toString();
        String password = passwordET.getText().toString();

        if (nameET.length() == 0) {

            nameET.setError("Please enter your user name");
        } else if (emailET.length() == 0) {
            emailET.setError("Please enter email address");
        } else if (firstNameET.length() == 0) {
            firstNameET.setError("Please enter your first name");
        } else if (lastNameET.length() == 0) {
            firstNameET.setError("Please enter your last name");
        } else if (contactET.length() < 11) {
            contactET.setError("Phone number should be 11 numbers");
        } else if (addressET.length() == 0) {
            addressET.setError("Please enter your address");
        } else if (passwordET.length() == 0) {
            passwordET.setError("Plase enter your password");
        } else if (passwordET.length() < 6) {
            passwordET.setError("Password should be 6 characters");

        } else {

            progressDialog.show();
            progressDialog.setMessage("Please wait it will take few moments");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);


            Call<RegistrationResponse> call = RetrofitClient.getInstance().getApi().register(userName, email, firstName, lastName,
                    password, address, contact);
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    RegistrationResponse registrationResponse = response.body();

                    if (registrationResponse != null) {
                        Toast.makeText(getApplicationContext(),"Register " + registrationResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "System is busy", Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    try {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}