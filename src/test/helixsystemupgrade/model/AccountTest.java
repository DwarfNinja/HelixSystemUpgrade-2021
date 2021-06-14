package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static final Account testAccount = new Account("Test Account", 1, "testaccountpassword",
            "user", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    @Test
    void testIsGeneratedRandomProductHistoryUnique() {
        for (int i = 0; i < testAccount.getProductHistoryList().size(); i++) {
            for (int j = i + 1; j < testAccount.getProductHistoryList().size(); j++) {
                if (testAccount.getProductHistoryList().get(i).equals(testAccount.getProductHistoryList().get(j))) {
                    throw new AssertionError("The generated Products in RandomProductHistory are not Unique");
                }
            }
        }
    }

    @Test
    void testEquals() {
        Product testProduct = new Product("test Product", 1, "10.00", "testSource.png");
        Account testAccount2 = new Account("Test Account2", 2, "testaccount2password",
                "user", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertFalse(testAccount.equals(testProduct));
        assertFalse(testAccount.equals(testAccount2));

        assertTrue(testAccount.equals(testAccount));
    }
}