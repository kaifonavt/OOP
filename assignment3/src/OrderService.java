import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public Order placeOrder(int customerId, List<OrderItem> items) {
        if (items.isEmpty())
            throw new RuntimeException("Order is empty");

        Order order = new Order(items, customerId);
        orders.add(order);
        return order;
    }

    public List<Order> getActiveOrders() {
        return orders.stream()
                .filter(o -> !o.isCompleted())
                .toList();
    }

    public Order getById(int id) {
        return orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }
}