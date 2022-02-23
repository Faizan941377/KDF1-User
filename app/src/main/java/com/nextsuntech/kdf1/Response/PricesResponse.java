package com.nextsuntech.kdf1.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.GetPricesDataModel;

import java.util.List;

public class PricesResponse {

    @SerializedName("result")
    @Expose
    private List<GetPricesDataModel> getPricesDataModelList = null;

    public List<GetPricesDataModel> getPricesDataModelList() {
        return getPricesDataModelList;
    }

    public void setGetPricesDataModelList(List<GetPricesDataModel> getPricesDataModelList) {
        this.getPricesDataModelList = getPricesDataModelList;
    }

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("productId")
    @Expose
    private Integer productId;

    @SerializedName("product")
    @Expose
    private String product;

    @SerializedName("choice")
    @Expose
    private String choice;

    @SerializedName("price")
    @Expose
    private Integer price;

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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

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
}
