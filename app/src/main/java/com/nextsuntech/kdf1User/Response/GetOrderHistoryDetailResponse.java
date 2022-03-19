package com.nextsuntech.kdf1User.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1User.Model.GetOrderHistoryDetailDataModel;

import java.util.List;

public class GetOrderHistoryDetailResponse {

    @SerializedName("result")
    @Expose
    private List<GetOrderHistoryDetailDataModel> result = null;

    public List<GetOrderHistoryDetailDataModel> getResult() {
        return result;
    }

    public void setResult(List<GetOrderHistoryDetailDataModel> result) {
        this.result = result;
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


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("productId")
        @Expose
        private Integer productId;
        @SerializedName("productName")
        @Expose
        private String productName;
        @SerializedName("customerName")
        @Expose
        private String customerName;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("imageName")
        @Expose
        private ImageName imageName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("totalQuantity")
        @Expose
        private Integer totalQuantity;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("cartAutoId")
        @Expose
        private Integer cartAutoId;
        @SerializedName("tableName")
        @Expose
        private String tableName;

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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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
}
