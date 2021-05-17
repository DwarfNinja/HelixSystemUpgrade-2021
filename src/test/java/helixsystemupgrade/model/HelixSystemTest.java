package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

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
}