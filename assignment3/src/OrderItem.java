public class OrderItem {
    private int id;
    private static int idGen;
    private int orederId;
    private int menuItemId;
    private String menuItemName;
    private int quantity;
    private double price;

    public OrderItem(int id, int orederId, int menuItemId, String menuItemName, int quantity, double price) {
        this.id = idGen++;
        this.orederId = orederId;
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
    public String getMenuItemName() {
        return menuItemName;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", menuItemName='" + menuItemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + this.getTotal() +
                '}';
    }
}
