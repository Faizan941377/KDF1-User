package com.nextsuntech.kdf1.Network;

import com.google.gson.JsonObject;
import com.nextsuntech.kdf1.Model.CheckOutDataModel;
import com.nextsuntech.kdf1.Model.GetCartDataModel;
import com.nextsuntech.kdf1.Response.AddToCartResponse;
import com.nextsuntech.kdf1.Response.BookingDetailsResponse;
import com.nextsuntech.kdf1.Response.CheckOutResponse;
import com.nextsuntech.kdf1.Response.DeleteCartProductResponse;
import com.nextsuntech.kdf1.Response.GetCartResponse;
import com.nextsuntech.kdf1.Response.GetProductResponse;
import com.nextsuntech.kdf1.Response.LoginResponse;
import com.nextsuntech.kdf1.Response.MenuResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
            @Field("UserRegistrationId") int UserRegistrationId,
            @Field("TotalQuantity") String TotalQuantity,
            @Field("Price") String Price
    );

    @FormUrlEncoded
    @POST("GetCarts")
    Call<GetCartResponse> getAddToCart(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("LoginWaiter")
    Call<LoginResponse> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("BookingDetailsAdd")
    Call<BookingDetailsResponse> bookingDetails(
            @Field("CustomerName") String CustomerName,
            @Field("TotalQuantity") int TotalQuantity,
            @Field("TotalPrice") int TotalPrice,
            @Field("CartAutoId") int CartAutoId
    );

    @FormUrlEncoded
    @POST("delete")
    Call<DeleteCartProductResponse> deleteProductByCart(
            @Field("id") int id
    );


    @POST("CheckOut")
    Call<CheckOutResponse> send(@Body JSONArray jsonObject1);
}
