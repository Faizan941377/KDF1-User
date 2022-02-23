package com.nextsuntech.kdf1.Model;

public class GetPricesDataModel {

    int id;
    int productId;
    String product;
    String choice;
    int price;

    public GetPricesDataModel(int id, int productId, String product, String choice, int price) {
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
