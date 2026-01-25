import java.util.List;

public class Order {
    private int id;
    private static int idGen = 1;
    private boolean completed;
    private int customerId;
    private List<OrderItem> items;

    public Order(List<OrderItem> items, int customerId) {
        this.id = idGen++;
        this.items = items;
        this.customerId = customerId;
        this.completed = false;
    }

    public int getId() { return id; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public double getTotalPrice(){
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }

    public int getCustomerId() {
        return customerId;
    }
    @Override
    public String toString() {
        return "Order #" + id +
                " | Customer " + customerId +
                " | Total $" + getTotalPrice() +
                " | Completed: " + completed;
    }

}
