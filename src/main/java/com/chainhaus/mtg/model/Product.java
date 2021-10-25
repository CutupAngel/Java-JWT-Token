package com.chainhaus.mtg.model;

/**
 * Created by Jamali on 10/4/2021.
 */
public class Product {
    private Long productId;
    private String productURL;
    private Long ownerId;
    private Double askingPrice;

    public Product(Long productId, String productURL, Long ownerId, Double askingPrice) {

        this.productId = productId;
        this.productURL = productURL;
        this.ownerId = ownerId;
        this.askingPrice = askingPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(Double askingPrice) {
        this.askingPrice = askingPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", ownerId=" + ownerId +
                ", askingPrice=" + askingPrice +
                '}';
    }

}
