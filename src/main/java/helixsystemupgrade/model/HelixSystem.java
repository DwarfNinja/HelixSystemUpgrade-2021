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
        generateRandomAccountList();
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

    private void generateRandomAccountList() {
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
}
