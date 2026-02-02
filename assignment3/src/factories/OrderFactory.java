package factories;

import entities.DineInOrder;
import entities.Order;
import entities.PickupOrder;

public class OrderFactory {
    public static Order createOrder(String type, int id, int customerId ,String status,boolean completed) {
        if (type == null) return null;

        return switch (type.toUpperCase()) {
            case "DELIVERY" -> Order.builder()
                    .setId(id)
                    .setCustomerId(customerId)
                    .setStatus(status)
                    .setCompleted(completed)
                    .setDeliveryAddress("Default Address")
                    .build();
            case "PICKUP" -> new PickupOrder(id, customerId ,status, completed);
            case "DINEIN" -> new DineInOrder(id, customerId , status , completed);
            default -> new Order(id, customerId, status, completed);
        };
    }
}