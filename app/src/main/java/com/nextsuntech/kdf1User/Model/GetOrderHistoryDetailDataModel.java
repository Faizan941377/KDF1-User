package com.nextsuntech.kdf1User.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1User.Response.GetOrderHistoryDetailResponse;

public class GetOrderHistoryDetailDataModel {

    String id;
    String productId;
    String productName;
    String customerName;
    String userId;
    @SerializedName("imageName")
    @Expose
    private GetOrderHistoryDetailResponse.ImageName imageName;
    String description;
    String price;
    String totalQuantity;
    String status;
    String date;
    Integer cartAutoId;
    String tableName;

    public GetOrderHistoryDetailDataModel(String id, String productId, String productName,
                                          String customerName, String userId, GetOrderHistoryDetailResponse.ImageName imageName,
                                          String description, String price, String totalQuantity,
                                          String status, String date, Integer cartAutoId, String tableName) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.customerName = customerName;
        this.userId = userId;
        this.imageName = imageName;
        this.description = description;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.status = status;
        this.date = date;
        this.cartAutoId = cartAutoId;
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetOrderHistoryDetailResponse.ImageName getImageName() {
        return imageName;
    }

    public void setImageName(GetOrderHistoryDetailResponse.ImageName imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCartAutoId() {
        return cartAutoId;
    }

    public void setCartAutoId(Integer cartAutoId) {
        this.cartAutoId = cartAutoId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
