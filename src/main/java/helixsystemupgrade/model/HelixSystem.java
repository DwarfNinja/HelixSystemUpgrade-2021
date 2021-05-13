package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class HelixSystem {
    private String name;

    private List<Account> accountList = new ArrayList<>();

    private List<Product> inventoryList = new ArrayList<>();

    public HelixSystem(String name) {
        this.name = name;
        addDummyAccounts();
        generateRandomProductHistory();
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Product> getInventoryList() {
        return inventoryList;
    }

    public Account getAccountbyID(int id) {
        for (Account account : accountList) {
            if (account.getAccountID()== id) {
                return account;
            }
        }
        return null;
    }

    public Product getProductbyID(int id) {
        for (Product product : inventoryList) {
            if (product.getProductID()== id) {
                return product;
            }
        }
        return null;
    }

    private void addDummyAccounts() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(1, 8);
        for (int i = 0; i < randomAmountOfProducts; i++) {
            try {
                Account account = mapper.readValue(JsonUtils.getRandomObjectFromArray("json/all-accounts.json"), Account.class);
                accountList.add(account);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateRandomProductHistory() {
        ObjectMapper mapper = new ObjectMapper();
        for (Account account : accountList) {
            int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(1, 8);
            for (int i = 0; i < randomAmountOfProducts; i++) {
                try {
                    Product product = mapper.readValue(JsonUtils.getRandomObjectFromArray("json/all-products.json"), Product.class);
                    account.addToProductHistoryList(product);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
