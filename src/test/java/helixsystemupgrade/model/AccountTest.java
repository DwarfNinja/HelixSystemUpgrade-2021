package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

class AccountTest {

    private static final Account testAccount = new Account("Test Account", 1);

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
}