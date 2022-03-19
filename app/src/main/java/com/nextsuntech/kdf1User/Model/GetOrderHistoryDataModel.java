package com.nextsuntech.kdf1User.Model;

public class GetOrderHistoryDataModel {

    Integer userRegistration;
    String customerName;
    Integer id;
    Integer cartAutoId;
    String createAt;
    String status;
    Integer totalPrice;
    Integer totalQuantity;

    public GetOrderHistoryDataModel(Integer userRegistration, String customerName, Integer id,
                                    Integer cartAutoId, String createAt, String status, Integer totalPrice, Integer totalQuantity) {
        this.userRegistration = userRegistration;
        this.customerName = customerName;
        this.id = id;
        this.cartAutoId = cartAutoId;
        this.createAt = createAt;
        this.status = status;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

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