package com.nextsuntech.kdf1User.Users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nextsuntech.kdf1User.R;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView loginTV;
    EditText nameET;
    EditText contactET;
    EditText emailET;
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
        contactET = findViewById(R.id.et_register_contact);
        emailET = findViewById(R.id.et_register_email);
        passwordET = findViewById(R.id.et_register_password);
        addressET = findViewById(R.id.et_register_address);
        registerBT = findViewById(R.id.bt_register);

        nameET.setText("sha");
        contactET.setText("+923000941388");
        emailET.setText("s@gmail.com");
        passwordET.setText("123456");
        addressET.setText("Peshawar City");

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

        String name = nameET.getText().toString().trim();
        String contact = contactET.getText().toString().trim();
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String address = addressET.getText().toString().trim();

        if (nameET.length() == 0) {
            nameET.setError("Enter your name");
        } else if (contactET.length() == 0) {
            contactET.setError("Enter your Mobile no");
        } else if (contactET.length() < 11) {
            contactET.setError("Please check your mobile no");
        } else if (emailET.length() == 0) {
            emailET.setError("Enter your email");
        } else if (passwordET.length() == 0) {
            passwordET.setError("Enter your Password");
        } else if (passwordET.length() < 6) {
            passwordET.setError("password should be 6 characters");
        }
        if (addressET.length() == 0) {
            addressET.setError("Enter your address");
        } else {

            progressDialog.show();
            progressDialog.setMessage("Please wait it will take few moments");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);


        }
    }
}