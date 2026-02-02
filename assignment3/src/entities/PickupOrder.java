package entities;
public class PickupOrder extends Order {
    public PickupOrder(int id, int customerId ,String status, boolean completed) {
        super(id, customerId, status, completed);
    }
}
