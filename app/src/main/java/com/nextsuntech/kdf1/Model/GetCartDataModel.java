package com.nextsuntech.kdf1.Model;

public class GetCartDataModel {

    String id;
    String productId;
    String description;
    String price;
    String userRegistrationId;
    String totalQuantity;
    String cartAutoId;

    public GetCartDataModel(String id, String productId, String description, String price,
                            String userRegistrationId, String totalQuantity, String cartAutoId) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.userRegistrationId = userRegistrationId;
        this.totalQuantity = totalQuantity;
        this.cartAutoId = cartAutoId;
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

    public String getUserRegistrationId() {
        return userRegistrationId;
    }

    public void setUserRegistrationId(String userRegistrationId) {
        this.userRegistrationId = userRegistrationId;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getCartAutoId() {
        return cartAutoId;
    }

    public void setCartAutoId(String cartAutoId) {
        this.cartAutoId = cartAutoId;
    }
}
