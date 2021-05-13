package helixsystemupgrade.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Product.class)
public class Product {
    private String productName;
    private int productID;
    private double productPrice;
    private String imgSource;

    public Product() {
    }

    public Product(String productName, int productID, double productPrice, String imgSource) {
        this.productName = productName;
        this.productID = productID;
        this.productPrice = productPrice;
        this.imgSource = imgSource;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductID() {
        return productID;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getImgSource() {
        return imgSource;
    }

}
