public class OrderItem {
    private int menuItemId;
    private String menuItemName;
    private int quantity;
    private double price;

    public OrderItem( int menuItemId, String menuItemName, int quantity, double price) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotal(){
        return quantity * price;
    }
    public int getMenuItemId() {
        return menuItemId;
    }
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
         return menuItemName + " x" + quantity + " = $" + getTotal();
    }
}
