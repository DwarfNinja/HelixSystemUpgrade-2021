package helixsystemupgrade.model;

public class InventoryItem {
    private final int amount;
    private final Product product;

    public InventoryItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }
}
