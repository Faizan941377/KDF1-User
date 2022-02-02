package com.nextsuntech.kdf1.Network;

import com.nextsuntech.kdf1.Response.AddToCartResponse;
import com.nextsuntech.kdf1.Response.GetCartResponse;
import com.nextsuntech.kdf1.Response.GetProductResponse;
import com.nextsuntech.kdf1.Response.LoginResponse;
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

    @FormUrlEncoded
    @POST("AddCart")
    Call<AddToCartResponse> AddToCart(
            @Field("ProductId") String ProductId,
            @Field("UserRegistrationId") String UserRegistrationId,
            @Field("TotalQuantity") String TotalQuantity,
            @Field("Price") String Price
    );

    @FormUrlEncoded
    @POST("GetCarts")
    Call<GetCartResponse> getAddToCart(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("LoginWaiter")
    Call<LoginResponse> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );
}
