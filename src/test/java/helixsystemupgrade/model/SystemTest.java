package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemTest {

    private static final System testSystem = new System();

    @Test
    void testIsGeneratedAccountListUnique() {
        for (int i = 0; i < testSystem.getAccountList().size(); i++) {
            for (int j = i + 1; j < testSystem.getAccountList().size(); j++) {
                if (testSystem.getAccountList().get(i).equals(testSystem.getAccountList().get(j))) {
                    throw new AssertionError("The generated Accounts in AccountList are not Unique!");
                }
            }
        }
    }

    @Test
    void testIsGeneratedRandomHelixAccessUnique() {
        for (Account account : testSystem.getAccountList()) {
            for (int i = 0; i < account.getHelixAccessList().size(); i++) {
                for (int j = i + 1; j < account.getHelixAccessList().size(); j++) {
                    if (account.getHelixAccessList().get(i).equals(account.getHelixAccessList().get(j))) {
                        throw new AssertionError("The generated HelixAccessList for the Accounts are not Unique");
                    }
                }
            }
        }
    }

    @Test
    void testGetAccountByID() {
        int largestAccountID = 0;
        for (Account account : testSystem.getAccountList()) {
            if (account.getAccountID() > largestAccountID) {
                largestAccountID = account.getAccountID();
            }
        }
        assertNull(testSystem.getAccountByID(largestAccountID + 1));
        assertTrue(testSystem.getAccountByID(largestAccountID) instanceof Account);
    }
}