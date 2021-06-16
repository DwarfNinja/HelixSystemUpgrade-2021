package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product testProduct1 = new Product("Test  Product 1", 0, "20.00", "testSource.png");

    @Test
    void testEquals() {
        Product testProduct2 = new Product("Test Product 2", 1, "10.00", "testSource.png");
        Account testAccount = new Account("Test Account 2", 2, "testaccount2password",
                "user", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertNotEquals(testProduct1, testProduct2);
        assertNotEquals(testProduct1, testAccount);

        assertEquals(testProduct1, testProduct1);
    }
}
