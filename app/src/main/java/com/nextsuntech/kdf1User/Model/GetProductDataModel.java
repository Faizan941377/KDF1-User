package com.nextsuntech.kdf1User.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1User.Response.GetProductResponse;

import java.util.List;

public class GetProductDataModel {

    String id;
    String title;
    String price;
    String stockstatus;
    String description;
    String menu;

    @SerializedName("imageName")
    @Expose
    private List<GetProductResponse.ImageName> imageName = null;

    @SerializedName("productprice")
    @Expose
    private List<GetProductResponse.Productprice> productprice = null;


    public GetProductDataModel(String id, String title, String price, String stockstatus, String description, String menu) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stockstatus = stockstatus;
        this.description = description;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GetProductResponse.ImageName> getImageName() {
        return imageName;
    }

    public void setImageName(List<GetProductResponse.ImageName> imageName) {
        this.imageName = imageName;
    }

    public List<GetProductResponse.Productprice> getProductprice() {
        return productprice;
    }

    public void setProductprice(List<GetProductResponse.Productprice> productprice) {
        this.productprice = productprice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}