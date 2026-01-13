import java.util.List;

public class Order implements OrderService{
    private int id;
    private static int idGen;
    private boolean completed;
    private int customerId;
    private List<OrderItem> items;

    public Order(int id, List<OrderItem> items, int customerId, boolean completed) {
        this.id = idGen++;
        this.items = items;
        this.customerId = customerId;
        this.completed = false;
    }

    public void setCompleted(boolean completed) {this.completed = completed;}
    public boolean getCompleted(){return completed;}
    public void addItem(OrderItem item){
        items.add(item);
    }
    public double getTotalPrice(){
        double total = 0;
        for (OrderItem item : items){
            total += item.getTotal();
        }
        return total;
    }
    public List<OrderItem> getItems() {
        return items;
    }

    public int getCustomerId() {
        return customerId;
    }
    @Override
    public String toString() {
        return "Order{id=" + id +
                OrderItem.toString() + '\'' +
                ", copleted='" + completed +
                ", totalPrice=" + this.getTotalPrice() + '}';
    }
}
