package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

public class HelixSystem {
    private final String name;

    private List<Account> accountList = new ArrayList<>();

    private List<InventoryEntry> inventoryList = new ArrayList<>();

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

    public List<InventoryEntry> getInventoryList() {
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

    public InventoryEntry getinventoryEntrybyID(int id) {
        for (InventoryEntry inventoryEntry : inventoryList) {
            if (inventoryEntry.getAmount() == id) {
                return inventoryEntry;
            }
        }
        return null;
    }

    private void generateRandomAccountList() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfAccounts = NumberUtils.getRandomNumberInRange(1, 8);
        JsonArray jsonArray = JsonUtils.getJsonArray(JsonUtils.readJsonValueFromFile("json/all-accounts.json"));
        assert jsonArray != null : "returned jsonArray of JsonUtils.getJsonArray is null!";

        for (int i = 0; i < randomAmountOfAccounts; i++) {
            try {
                Account account = mapper.readValue(JsonUtils.getRandomObjectFromJsonArray(jsonArray), Account.class);
                accountList.add(account);
                jsonArray.remove(account);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateRandomInventory() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(10, 30);
        JsonArray jsonArray = JsonUtils.getJsonArray(JsonUtils.readJsonValueFromFile("json/all-products.json"));
        assert jsonArray != null : "returned jsonArray of JsonUtils.getJsonArray is null!";

        for (int i = 0; i < randomAmountOfProducts; i++) {
            try {
                int randomAmount = NumberUtils.getRandomNumberInRange(1, 6);
                Product product = mapper.readValue(JsonUtils.getRandomObjectFromJsonArray(jsonArray), Product.class);
                InventoryEntry inventoryEntry = new InventoryEntry(randomAmount, product);
                inventoryList.add(inventoryEntry);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
