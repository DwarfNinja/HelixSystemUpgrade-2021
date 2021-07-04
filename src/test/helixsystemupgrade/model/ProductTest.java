package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product testProduct1 = new Product("Test  Product 1", 0, "20.00", "testSource.png");

    @Test
    void testEquals() {
        Product testProduct2 = new Product("Test Product 2", 1, "10.00", "testSource.png");
        Account testAccount1 = new Account(1, "Test Account 1", "testaccount1", "testaccount1password",
                "user","testaccount1@gmail.com", "+31612345678", "test",
                "tester", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertNotEquals(testProduct1, testProduct2);
        assertNotEquals(testProduct1, testAccount1);

        assertEquals(testProduct1, testProduct1);
    }
}
