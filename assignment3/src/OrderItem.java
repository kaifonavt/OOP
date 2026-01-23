public class OrderItem {
    private int id;
    private static int idGen;
    private int orderId;
    private int menuItemId;
    private String menuItemName;
    private int quantity;
    private double price;

    public OrderItem(int orderId, int menuItemId, String menuItemName, int quantity, double price) {
        this.id = idGen++;
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
