package com.nextsuntech.kdf1User.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1User.Model.BookingDetailsDataModel;
import com.nextsuntech.kdf1User.Model.CustomerBookingDetailsDataModel;

public class CustomerBookingDetailsResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private CustomerBookingDetailsDataModel customerBookingDetailsDataModel;

    public String getMessage() {
        return message;
    }

    public void setCustomerBookingDetailsDataModel(String message) {
        this.message = message;
    }

    public CustomerBookingDetailsDataModel getCustomerBookingDetailsDataModel() {
        return customerBookingDetailsDataModel;
    }

    public void setResult(CustomerBookingDetailsDataModel customerBookingDetailsDataModel) {
        this.customerBookingDetailsDataModel = customerBookingDetailsDataModel;
    }

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("totalQuantity")
    @Expose
    private Integer totalQuantity;

    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;

    @SerializedName("cartAutoId")
    @Expose
    private Integer cartAutoId;

    @SerializedName("createAt")
    @Expose
    private String createAtString;

    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCartAutoId() {
        return cartAutoId;
    }

    public void setCartAutoId(Integer cartAutoId) {
        this.cartAutoId = cartAutoId;
    }

    public String getCreateAtString() {
        return createAtString;
    }

    public void setCreateAtString(String createAtString) {
        this.createAtString = createAtString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
