package com.nextsuntech.kdf1.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.CheckOutDataModel;

public class CheckOutResponse {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private CheckOutDataModel checkOutDataModel;

    public String getMessage() {
        return message;
    }

    public void setCheckOutDataModel(String message) {
        this.message = message;
    }

    public CheckOutDataModel getCheckOutDataModel() {
        return checkOutDataModel;
    }

    public void setResult(CheckOutDataModel checkOutDataModel) {
        this.checkOutDataModel = checkOutDataModel;
    }


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customerName")
    @Expose
    private String customerName;
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
    private String createAt;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}