package com.nextsuntech.kdf1.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.GetCartDataModel;

import java.util.List;

public class GetCartResponse {


    @SerializedName("message")
    @Expose
    private List<GetCartDataModel> getCartDataModels = null;

    public List<GetCartDataModel> getCartDataModels() {
        return getCartDataModels;
    }

    public void setGetCartDataModels(List<GetCartDataModel> getCartDataModels) {
        this.getCartDataModels = getCartDataModels;
    }


    public class ImageName {

        @SerializedName("images")
        @Expose
        private String images;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

    }

    public class Message {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("productId")
        @Expose
        private Integer productId;
        @SerializedName("imageName")
        @Expose
        private ImageName imageName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("userRegistrationId")
        @Expose
        private Integer userRegistrationId;
        @SerializedName("totalQuantity")
        @Expose
        private Integer totalQuantity;
        @SerializedName("cartAutoId")
        @Expose
        private Integer cartAutoId;

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

        public ImageName getImageName() {
            return imageName;
        }

        public void setImageName(ImageName imageName) {
            this.imageName = imageName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
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

        public Integer getCartAutoId() {
            return cartAutoId;
        }

        public void setCartAutoId(Integer cartAutoId) {
            this.cartAutoId = cartAutoId;
        }

    }

}
