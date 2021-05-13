package helixsystemupgrade.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.ArrayList;

@JsonDeserialize(as = Account.class)
public class Account {
    private String accountName;
    private int accountID;
    private List<Product> productHistoryList = new ArrayList<>();

    public Account(){
    }
    
    public Account(String accountName, int accountID) {
        this.accountName = accountName;
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountID() {
        return accountID;
    }

    public List<Product> getProductHistoryList() {
        return productHistoryList;
    }

    public void addToProductHistoryList(Product product) {
        productHistoryList.add(product);
    }

    private void addDummyProducts() {
        List<Product> allProductsList = new ArrayList<>();
    }

}
