package entities;
public class DeliveryOrder extends Order {
    public DeliveryOrder(int id, int customerId, String status ,boolean completed) {
        super(id, customerId, status, completed);
    }
}
