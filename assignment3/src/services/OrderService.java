package services;

import entities.Order;
import entities.OrderItem;
import entities.MenuItem;
import exceptions.InvalidQuantityException;
import exceptions.MenuItemNotAvailableException;
import exceptions.OrderNotFoundException;
import repositories.IMenuItemRepository;
import repositories.IOrderRepository;

import java.sql.SQLException;
import java.util.*;

public class OrderService {
    private final IMenuItemRepository menuRepo;
    private final IOrderRepository orderRepo;

    public OrderService(IMenuItemRepository menuRepo, IOrderRepository orderRepo) {
        this.menuRepo = menuRepo;
        this.orderRepo = orderRepo;
    }

    public Order placeOrder(int customerId, List<OrderItem> orderItems)
            throws SQLException, InvalidQuantityException, MenuItemNotAvailableException {

        for (OrderItem item : orderItems) {
            validateStock(item.getMenuItemId(), item.getQuantity());
        }

        Order order = new Order(customerId);
        order.setItems(orderItems);

        // This replaces activeOrders.put()
        orderRepo.create(order);
        return order;
    }

    public List<Order> getActiveOrders() throws SQLException {
        return orderRepo.findAllActive();
    }

    public void markOrderAsCompleted(int orderId) throws OrderNotFoundException, SQLException {
        Order order = orderRepo.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found in DB.");
        }
        orderRepo.updateStatus(orderId, true);
    }

    public void validateStock(int itemId, int requestedQty) throws MenuItemNotAvailableException, SQLException, InvalidQuantityException {
        MenuItem item = menuRepo.findById(itemId);
        if (requestedQty <= 0) {
            throw new InvalidQuantityException("Quantity must be at least 1.");
        }
        if (item == null) throw new MenuItemNotAvailableException("Item ID " + itemId + " not found.");
        if (item.getQuantity() < requestedQty) throw new InvalidQuantityException("Out of stock.");
    }

    public Order getOrderById(int orderId) throws SQLException {
        return orderRepo.findById(orderId);
    }
}