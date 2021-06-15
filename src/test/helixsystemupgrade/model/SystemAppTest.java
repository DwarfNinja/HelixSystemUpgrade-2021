package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemAppTest {

    private static final SystemApp TEST_SYSTEMAPP = SystemApp.getTheSystemApp();

    @Test
    void testIsGeneratedAccountListUnique() {
        for (int i = 0; i < TEST_SYSTEMAPP.getAccountList().size(); i++) {
            for (int j = i + 1; j < TEST_SYSTEMAPP.getAccountList().size(); j++) {
                if (TEST_SYSTEMAPP.getAccountList().get(i).equals(TEST_SYSTEMAPP.getAccountList().get(j))) {
                    throw new AssertionError("The generated Accounts in AccountList are not Unique!");
                }
            }
        }
    }

    @Test
    void testIsGeneratedRandomHelixAccessUnique() {
        for (Account account : TEST_SYSTEMAPP.getAccountList()) {
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
        for (Account account : TEST_SYSTEMAPP.getAccountList()) {
            if (account.getAccountID() > largestAccountID) {
                largestAccountID = account.getAccountID();
            }
        }
        assertNull(TEST_SYSTEMAPP.getAccountByID(largestAccountID + 1));
        assertTrue(TEST_SYSTEMAPP.getAccountByID(largestAccountID) instanceof Account);
    }
}