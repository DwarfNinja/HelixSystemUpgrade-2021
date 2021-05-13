package helixsystemupgrade.model;

import java.util.List;
import java.util.ArrayList;

public class Account {
    private String accountName;
    private int accountID;
    private List<Product> productHistoryList = new ArrayList<>();

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
