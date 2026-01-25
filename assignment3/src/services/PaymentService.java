public class PaymentService {
    public void processPayment(Order order) {
        if (order.isCompleted())
            throw new RuntimeException("Order already paid");

        System.out.println("Payment successful: $" + order.getTotalPrice());
        order.setCompleted(true);
    }
}

