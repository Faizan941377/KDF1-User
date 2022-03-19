package com.nextsuntech.kdf1User.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1User.Model.GetOrderHistoryDataModel;

import java.util.List;

public class GetOrderHistoryResponse {


    @SerializedName("result")
    @Expose
    private List<GetOrderHistoryDataModel> getOrderHistoryDataModelList = null;

    public List<GetOrderHistoryDataModel> getGetOrderHistoryDataModelList() {
        return getOrderHistoryDataModelList;
    }

    public void setGetOrderHistoryDataModelList(List<GetOrderHistoryDataModel> getOrderHistoryDataModelList) {
        this.getOrderHistoryDataModelList = getOrderHistoryDataModelList;
    }

    @SerializedName("userRegistration")
    @Expose
    private Integer userRegistration;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cartAutoId")
    @Expose
    private Integer cartAutoId;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;
    @SerializedName("totalQuantity")
    @Expose
    private Integer totalQuantity;

    public Integer getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(Integer userRegistration) {
        this.userRegistration = userRegistration;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

}
