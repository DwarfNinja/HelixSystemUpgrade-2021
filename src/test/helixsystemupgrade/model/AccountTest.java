package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account testAccount1 = new Account(1, "Test Account 1", "testaccount1", "testaccount1password",
            "user","testaccount1@gmail.com", "+31612345678", "test",
            "tester", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

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
        Account testAccount2 = new Account(2, "Test Account 2", "testaccount2", "testaccount2password",
                "user","testaccount2@gmail.com", "+31612345678", "test",
                "tester", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertNotEquals(testAccount1, testProduct);
        assertNotEquals(testAccount1, testAccount2);

        assertEquals(testAccount1, testAccount1);
    }
}