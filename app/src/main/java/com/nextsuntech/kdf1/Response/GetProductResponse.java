package com.nextsuntech.kdf1.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.GetProductDataModel;

import java.io.Serializable;
import java.util.List;

public class GetProductResponse {

    @SerializedName("result")
    @Expose
    private List<GetProductDataModel> getProductDataModelList = null;

    public List<GetProductDataModel> getProductDataModelList() {
        return getProductDataModelList;
    }

    public void setGetProductDataModelList(List<GetProductDataModel> getProductDataModelList) {
        this.getProductDataModelList = getProductDataModelList;
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

    public class Productprice {

        @SerializedName("choice")
        @Expose
        private String choice;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
    public class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("productprice")
        @Expose
        private List<Productprice> productprice = null;
        @SerializedName("stockstatus")
        @Expose
        private String stockstatus;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("menu")
        @Expose
        private String menu;
        @SerializedName("imageName")
        @Expose
        private List<ImageName> imageName = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public List<Productprice> getProductprice() {
            return productprice;
        }

        public void setProductprice(List<Productprice> productprice) {
            this.productprice = productprice;
        }

        public String getStockstatus() {
            return stockstatus;
        }

        public void setStockstatus(String stockstatus) {
            this.stockstatus = stockstatus;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public List<ImageName> getImageName() {
            return imageName;
        }

        public void setImageName(List<ImageName> imageName) {
            this.imageName = imageName;
        }

    }

}