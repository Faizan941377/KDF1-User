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
    String menu;
    String image;

    public GetProductDataModel(String id, String title, String price, String stockstatus, String description, String menu, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stockstatus = stockstatus;
        this.description = description;
        this.menu = menu;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}