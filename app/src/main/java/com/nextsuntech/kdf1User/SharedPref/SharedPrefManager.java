package com.nextsuntech.kdf1User.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.nextsuntech.kdf1User.Model.LoginDataModel;

public class SharedPrefManager {
    public static final String SHARED_PREF_NAME = "credentials";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveLoginUser(LoginDataModel LoginDataModelList) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", Integer.parseInt(String.valueOf((LoginDataModelList.getId()))));
        editor.putString("userName", LoginDataModelList.getUserName());
        editor.putString("email", LoginDataModelList.getEmail());
        editor.putString("firstName", LoginDataModelList.getFirstName());
        editor.putString("lastName", LoginDataModelList.getLastName());
        editor.putString("address",LoginDataModelList.getAddress());
        editor.putString("mobileNo",LoginDataModelList.getMobileNo());
        editor.putString("password", LoginDataModelList.getPassword());
        editor.putString("createAt", LoginDataModelList.getCreateAt());
        editor.putString("status", LoginDataModelList.getStatus());
        editor.putString("type", LoginDataModelList.getType());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public LoginDataModel getSavedUsers() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new LoginDataModel(
                sharedPreferences.getInt("id",0),
                sharedPreferences.getString("userName", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("firstName", null),
                sharedPreferences.getString("lastName", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("mobileNo", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("createAt", null),
                sharedPreferences.getString("status", null),
                sharedPreferences.getString("type", null)

        );

    }


    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
