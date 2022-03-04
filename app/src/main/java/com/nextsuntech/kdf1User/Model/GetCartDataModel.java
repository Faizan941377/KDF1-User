package com.nextsuntech.kdf1User.Model;

import com.nextsuntech.kdf1User.Response.GetCartResponse;

public class GetCartDataModel {

    int id;
    int productId;
    String description;
    int price;
    int userRegistrationId;
    int totalQuantity;
    String cartAutoId;

    public GetCartResponse.ImageName imageName;
    public GetCartResponse.Message message;

    public GetCartDataModel(int id, int productId, String description, int price, int userRegistrationId, int totalQuantity,
                            String cartAutoId, GetCartResponse.ImageName imageName, GetCartResponse.Message message) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.userRegistrationId = userRegistrationId;
        this.totalQuantity = totalQuantity;
        this.cartAutoId = cartAutoId;
        this.imageName = imageName;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserRegistrationId() {
        return userRegistrationId;
    }

    public void setUserRegistrationId(int userRegistrationId) {
        this.userRegistrationId = userRegistrationId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getCartAutoId() {
        return cartAutoId;
    }

    public void setCartAutoId(String cartAutoId) {
        this.cartAutoId = cartAutoId;
    }

    public GetCartResponse.ImageName getImageName() {
        return imageName;
    }

    public void setImageName(GetCartResponse.ImageName imageName) {
        this.imageName = imageName;
    }

    public GetCartResponse.Message getMessage() {
        return message;
    }

    public void setMessage(GetCartResponse.Message message) {
        this.message = message;
    }
}
