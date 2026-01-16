import java.util.List;

public class Order implements OrderService{
    private int id;
    private static int idGen;
    private boolean completed;
    private int customerId;
    private String address;
    private List<OrderItem> items;

    public Order(List<OrderItem> items, int customerId, String address) {
        this.id = idGen++;
        this.items = items;
        this.customerId = customerId;
        setCompleted(false);
        setAddress(address);
    }
    public Order() {
        this.id = idGen++;
        this.customerId = 0;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if ((address == null)||(address.trim().isEmpty()))
        {throw new IllegalArgumentException("Address line cannot be empty");}
        else {this.address = address;}
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
                items.toString() + '\'' +
                ", copleted='" + completed +
                ", totalPrice=" + this.getTotalPrice() + '}';
    }
    @Override
    public void editOrder(int id, List<OrderItem> items, int customerId, String address, boolean completed) {
        this.id = idGen++;
        this.items = items;
        this.customerId = customerId;
        setCompleted(false);
        setAddress(address);
    }
    @Override
    public void deleteOrder(int id) {
        items.remove(id);
    }
    @Override
    public void findOrder(int id) {
        items.get(id).toString();
    }
}
