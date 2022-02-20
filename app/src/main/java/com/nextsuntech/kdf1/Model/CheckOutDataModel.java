package com.nextsuntech.kdf1.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Response.CheckOutResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CheckOutDataModel {


    @SerializedName("cart")
    @Expose
    private List<Cart> cart = null;

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }


    public static class Cart {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("ProductId")
        @Expose
        private Integer productId;
        @SerializedName("UserRegistrationId")
        @Expose
        private Integer userRegistrationId;
        @SerializedName("TotalQuantity")
        @Expose
        private Integer totalQuantity;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("Price")
        @Expose
        private Integer price;

        public Cart(Integer id, Integer productId, Integer userRegistrationId, Integer totalQuantity, String status, Integer price) {
            this.id = id;
            this.productId = productId;
            this.userRegistrationId = userRegistrationId;
            this.totalQuantity = totalQuantity;
            this.status = status;
            this.price = price;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getUserRegistrationId() {
            return userRegistrationId;
        }

        public void setUserRegistrationId(Integer userRegistrationId) {
            this.userRegistrationId = userRegistrationId;
        }

        public Integer getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(Integer totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }

    public class CheckOutResponse {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("autoId")
        @Expose
        private Integer autoId;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getAutoId() {
            return autoId;
        }

        public void setAutoId(Integer autoId) {
            this.autoId = autoId;
        }

    }

}