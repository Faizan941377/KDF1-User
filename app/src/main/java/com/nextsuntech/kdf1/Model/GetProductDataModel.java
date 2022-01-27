package com.nextsuntech.kdf1.Model;

import android.media.Image;

import com.nextsuntech.kdf1.Response.GetProductResponse;

import java.util.ArrayList;
import java.util.List;

public class GetProductDataModel {

    String id;
    String title;
    String price;
    String stockstatus;
    String description;
    String images;

    public GetProductDataModel(String id, String title, String price, String stockstatus, String description, String images) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stockstatus = stockstatus;
        this.description = description;
        this.images = images;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
