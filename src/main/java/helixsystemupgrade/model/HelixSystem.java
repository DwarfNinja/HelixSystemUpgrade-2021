package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelixSystem {
    private final String name;

    private List<Account> accountList = new ArrayList<>();

    private List<HashMap<String, Object>> inventoryList = new ArrayList<>();

    public HelixSystem(String name) {
        this.name = name;
        generateRandomAccountList();
        generateRandomInventory();
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<HashMap<String, Object>> getInventoryList() {
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

    public HashMap<String, Object> getinventoryItembyID(int id) {
        for (HashMap<String, Object> inventoryItem : inventoryList) {
            Product product = (Product) inventoryItem.get("product");
            if (product.getProductID()== id) {
                return inventoryItem;
            }
        }
        return null;
    }

    private void generateRandomAccountList() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfAccounts = NumberUtils.getRandomNumberInRange(1, 8);
        for (int i = 0; i < randomAmountOfAccounts; i++) {
            try {
                Account account = mapper.readValue(JsonUtils.getRandomObjectFromArray("json/all-accounts.json"), Account.class);
                accountList.add(account);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateRandomInventory() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(10, 30);
        for (int i = 0; i < randomAmountOfProducts; i++) {
            try {
                int randomAmount = NumberUtils.getRandomNumberInRange(1, 6);
                Product product = mapper.readValue(JsonUtils.getRandomObjectFromArray("json/all-products.json"), Product.class);
                HashMap<String, Object> inventoryItem = new HashMap<>();
                inventoryItem.put("amount", Integer.toString(randomAmount));
                inventoryItem.put("product", product);
                inventoryList.add(inventoryItem);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
