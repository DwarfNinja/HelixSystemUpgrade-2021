package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static final Account testAccount1 = new Account("Test Account 1", 1, "testaccountpassword",
            "user", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    @Test
    void testIsGeneratedRandomProductHistoryUnique() {
        for (int i = 0; i < testAccount1.getProductHistoryList().size(); i++) {
            for (int j = i + 1; j < testAccount1.getProductHistoryList().size(); j++) {
                if (testAccount1.getProductHistoryList().get(i).equals(testAccount1.getProductHistoryList().get(j))) {
                    throw new AssertionError("The generated Products in RandomProductHistory are not Unique");
                }
            }
        }
    }

    @Test
    void testEquals() {
        Product testProduct = new Product("Test Product", 1, "10.00", "testSource.png");
        Account testAccount2 = new Account("Test Account 2", 2, "testaccount2password",
                "user", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertNotEquals(testAccount1, testProduct);
        assertNotEquals(testAccount1, testAccount2);

        assertEquals(testAccount1, testAccount1);
    }
}