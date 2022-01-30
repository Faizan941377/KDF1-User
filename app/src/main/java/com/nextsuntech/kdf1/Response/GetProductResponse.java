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
        private List<Object> productprice = null;
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

        public List<Object> getProductprice() {
            return productprice;
        }

        public void setProductprice(List<Object> productprice) {
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




   /* public class ImageName {

        @SerializedName("images")
        @Expose
        private String images;

        public ImageName(String images) {
            this.images = images;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("stockstatus")
        @Expose
        private String stockstatus;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("imageName")
        @Expose
        private ImageName imageName;

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

        public ImageName getImageName() {
            return imageName;
        }

        public void setImageName(ImageName imageName) {
            this.imageName = imageName;
        }

    }*/
