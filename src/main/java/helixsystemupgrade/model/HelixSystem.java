package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;
import java.util.ArrayList;
import java.util.List;

public class HelixSystem {
    private final String name;

    private List<Account> accountList = new ArrayList<>();

    private List<InventoryItem> inventoryList = new ArrayList<>();

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

    public List<InventoryItem> getInventoryList() {
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

    public InventoryItem getinventoryItembyID(int id) {
        for (InventoryItem inventoryItem : inventoryList) {
            if (inventoryItem.getAmount() == id) {
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
                InventoryItem inventoryItem = new InventoryItem(randomAmount, product);
                inventoryList.add(inventoryItem);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
