package com.nextsuntech.kdf1.Model;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Response.GetProductResponse;

import java.util.ArrayList;
import java.util.List;

public class GetProductDataModel {

    String id;
    String title;
    String price;
    String stockstatus;
    String description;
    String menu;

    private GetProductResponse.ImageName imageName;

    public GetProductDataModel(String id, String title, String price, String stockstatus,
                               String description, String menu, GetProductResponse.ImageName imageName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stockstatus = stockstatus;
        this.description = description;
        this.menu = menu;
        this.imageName = imageName;
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

    public GetProductResponse.ImageName getImageName() {
        return imageName;
    }

    public void setImageName(GetProductResponse.ImageName imageName) {
        this.imageName = imageName;
    }
}