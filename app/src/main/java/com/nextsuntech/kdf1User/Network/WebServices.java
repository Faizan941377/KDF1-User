package com.nextsuntech.kdf1User.Network;

import com.nextsuntech.kdf1User.Response.AddToCartResponse;
import com.nextsuntech.kdf1User.Response.BookingDetailsResponse;
import com.nextsuntech.kdf1User.Response.CheckOutResponse;
import com.nextsuntech.kdf1User.Response.CustomerBookingDetailsResponse;
import com.nextsuntech.kdf1User.Response.DeleteCartProductResponse;
import com.nextsuntech.kdf1User.Response.GetCartResponse;
import com.nextsuntech.kdf1User.Response.GetOrderHistoryDetailResponse;
import com.nextsuntech.kdf1User.Response.GetOrderHistoryResponse;
import com.nextsuntech.kdf1User.Response.GetProductResponse;
import com.nextsuntech.kdf1User.Response.LoginResponse;
import com.nextsuntech.kdf1User.Response.MenuResponse;
import com.nextsuntech.kdf1User.Response.PricesResponse;
import com.nextsuntech.kdf1User.Response.RegistrationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    @POST("LoginCustomer")
    Call<LoginResponse> loginResponse(
            @Field("Email") String Email,
            @Field("Password") String Password
    );

    @FormUrlEncoded
    @POST("CustomerRegister")
    Call<RegistrationResponse> register(
            @Field("UserName")String UserName,
            @Field("Email")String Email,
            @Field("FirstName")String FirstName,
            @Field("LastName")String LastName,
            @Field("Password")String Password,
            @Field("Address")String Address,
            @Field("MobileNo")String MobileNo
    );

    @FormUrlEncoded
    @POST("BookingDetailsAdd")
    Call<BookingDetailsResponse> bookingDetails(
            @Field("CustomerName") String CustomerName,
            @Field("TotalQuantity") int TotalQuantity,
            @Field("TotalPrice") int TotalPrice,
            @Field("CartAutoId") int CartAutoId,
            @Field("CreateAt") String CreateAt
    );

    @FormUrlEncoded
    @POST("delete")
    Call<DeleteCartProductResponse> deleteProductByCart(
            @Field("id") int id
    );


    @POST("CheckOut")
    Call<CheckOutResponse> postOrder(@Body RequestBody requestBody);

    @FormUrlEncoded
    @POST("GetPrices")
    Call<PricesResponse> getPrices(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("CustomerBookingDetailsAdd")
    Call<CustomerBookingDetailsResponse> customerBookingDetails(
            @Field("PhoneNumber") String PhoneNumber,
            @Field("TotalQuantity") int TotalQuantity,
            @Field("TotalPrice") int TotalPrice,
            @Field("CartAutoId") int CartAutoId,
            @Field("CreateAt") String CreateAt,
            @Field("Email") String Email,
            @Field("City") String City,
            @Field("Address") String Address
    );

    @GET("GetInvices")
    Call<GetOrderHistoryResponse> getOrderHistory();


    @FormUrlEncoded
    @POST("GetInvicesDetails")
    Call<GetOrderHistoryDetailResponse> getOrderHistoryDetails(
            @Field("CartAutoId") Integer  id
    );

    @FormUrlEncoded
    @POST("delete")
    Call<DeleteCartProductResponse> deleteOder(
            @Field("id") int id
    );
}
