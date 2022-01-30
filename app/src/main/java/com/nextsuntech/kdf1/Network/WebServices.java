package com.nextsuntech.kdf1.Network;

import com.nextsuntech.kdf1.Response.GetProductResponse;
import com.nextsuntech.kdf1.Response.MenuResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {


    @GET("GetMenuCata")
    Call<MenuResponse> menuResponse();

    @FormUrlEncoded
    @POST("GetProductDetails")
    Call<GetProductResponse> getProductResponse(
            @Field("id") String id
    );
}
