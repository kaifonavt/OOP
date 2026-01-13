import java.util.List;

public interface OrderService {
    void editOrder(int id, List<OrderItem> items, int customerId, String address, boolean completed);
    void deleteOrder(int id);
    void findOrder(int id);
}
