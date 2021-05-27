package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelixSystemTest {

    private static final HelixSystem testHelixSystem = new HelixSystem("TestHelixSystem");

    @Test
    void testIsGeneratedRandomInventoryUnique() {
        for (int i = 0; i < testHelixSystem.getInventoryList().size(); i++) {
            for (int j = i + 1; j < testHelixSystem.getInventoryList().size(); j++) {
                if (testHelixSystem.getInventoryList().get(i).getProduct().equals(testHelixSystem.getInventoryList().get(j).getProduct())) {
                    throw new AssertionError("The generated InventoryEntries[Product] in InventoryList are not Unique!");
                }
            }
        }
    }

    @Test
    void testIsGeneratedRandomInventoryOfCorrectSize() {
        assertTrue(testHelixSystem.getInventoryList().size() >= 5 && testHelixSystem.getInventoryList().size() <= 30);
    }

    @Test
    void testGetInventoryEntryByID() {
        int largestInventoryEntryID = 0;
        for (InventoryEntry inventoryEntry : testHelixSystem.getInventoryList()) {
            if (inventoryEntry.getProduct().getProductID() > largestInventoryEntryID) {
                largestInventoryEntryID = inventoryEntry.getProduct().getProductID();
            }
        }
        assertNull(testHelixSystem.getInventoryEntrybyID(largestInventoryEntryID + 1));
    }
}
