package com.nextsuntech.kdf1.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.GetProductDataModel;

import java.util.List;

public class GetProductResponse {


    @SerializedName("result")
    @Expose
    private List<GetProductDataModel> getProductDataModels = null;

    public List<GetProductDataModel> getProductDataModelList() {
        return getProductDataModels;
    }

    public void setGetProductDataModels(List<GetProductDataModel> getProductDataModels) {
        this.getProductDataModels = getProductDataModels;
    }


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
    private String imageName;

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

    public String  getImageName() {
        return imageName;
    }

    public void setImageName(String  imageName) {
        this.imageName = imageName;
    }

}
