package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import javax.json.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class HelixSystem {
    private final String helixSystemName;

    private final List<InventoryEntry> inventoryList = new ArrayList<>();

    public HelixSystem(String helixSystemName) {
        this.helixSystemName = helixSystemName;
        generateRandomInventory();
    }

    public String getHelixSystemName() {
        return helixSystemName;
    }

    public List<InventoryEntry> getInventoryList() {
        return inventoryList;
    }


    public InventoryEntry getInventoryEntrybyID(int id) {
        for (InventoryEntry inventoryEntry : inventoryList) {
            if (inventoryEntry.getProduct().getProductID() == id) {
                return inventoryEntry;
            }
        }
        return null;
    }

    //TODO: UNIQUE INVENTORY GENERATION COULD BE DONE BETTER
    private void generateRandomInventory() {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray jsonArray = JsonUtils.getJsonArray(JsonUtils.readJsonValueFromFile("json/all-products.json"));
        assert jsonArray != null : "returned jsonArray of JsonUtils.getJsonArray is null!";
        int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(5, Math.min(jsonArray.size(), 30));
        ArrayList<InventoryEntry> randomInventoryEntryList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.size(); i++ ) {
                Product product = mapper.readValue(JsonUtils.getObjectFromJsonArray(jsonArray, jsonArray.get(i)), Product.class);
                InventoryEntry inventoryEntry = createRandomInventoryEntry(product);
                randomInventoryEntryList.add(inventoryEntry);
            }
            for (int i = 0; i < randomAmountOfProducts; i++) {
                InventoryEntry randomInventoryEntry = randomInventoryEntryList.get(NumberUtils.getRandomNumberInRange(0, randomInventoryEntryList.size()));
                inventoryList.add(randomInventoryEntry);
                randomInventoryEntryList.remove(randomInventoryEntry);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private InventoryEntry createRandomInventoryEntry(Product product) {
        int randomAmount = NumberUtils.getRandomNumberInRange(1, 8);
        InventoryEntry inventoryEntry = new InventoryEntry(randomAmount, product);
        return inventoryEntry;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HelixSystem) {
            return ((HelixSystem) obj).getHelixSystemName().equals(getHelixSystemName());
        }
        return false;
    }
}
