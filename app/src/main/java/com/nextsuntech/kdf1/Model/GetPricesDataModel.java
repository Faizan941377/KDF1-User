package com.nextsuntech.kdf1.Model;

public class GetPricesDataModel {

    int id;
    String productId;
    String product;
    String choice;
    String price;

    public GetPricesDataModel(int id, String productId, String product, String choice, String price) {
        this.id = id;
        this.productId = productId;
        this.product = product;
        this.choice = choice;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
