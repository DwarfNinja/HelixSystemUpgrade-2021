package helixsystemupgrade.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Product.class)
public class Product {
    private final String productName;
    private final int productID;
    private final double productPrice;
    private final String imgSource;

    @JsonCreator
    public Product(@JsonProperty("productName") String productName, @JsonProperty("productID") int productID,
                   @JsonProperty("productPrice") double productPrice, @JsonProperty("imgSource") String imgSource) {
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
