package com.nextsuntech.kdf1.Users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nextsuntech.kdf1.R;

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

        emailET.setText("david@123.com");
        passwordET.setText("12345");


        loginBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_login:
                Login();
                break;
        }
    }

    private void Login() {

        String email  = emailET.getText().toString().trim();
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


        }
    }
}