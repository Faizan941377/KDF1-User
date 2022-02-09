package com.nextsuntech.kdf1.Model;

public class CheckOutDataModel {

    int Id;
    String customerName;
    int totalQuantity;
    String totalPrice;
    int cartAutoId;
    String createAt;
    String status;

    public CheckOutDataModel(int id, String customerName, int totalQuantity, String totalPrice, int cartAutoId, String createAt, String status) {
        Id = id;
        this.customerName = customerName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.cartAutoId = cartAutoId;
        this.createAt = createAt;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCartAutoId() {
        return cartAutoId;
    }

    public void setCartAutoId(int cartAutoId) {
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
