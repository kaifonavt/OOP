package entities;
public class DineInOrder extends Order {
    public DineInOrder(int id, int customerId, String status, boolean completed) {
        super(id, customerId, status,completed);
    }
}