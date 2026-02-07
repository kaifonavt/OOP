package com.cafeteria.ordering;

import com.cafeteria.billing.TaxConfig;
import com.cafeteria.menumanagement.MenuItem;
import com.cafeteria.menumanagement.MenuItemNotAvailableException;
import com.cafeteria.menumanagement.IMenuItemRepository;

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

        Order order = Order.builder()
                .setCustomerId(customerId)
                .setItems(orderItems)
                .setStatus("NEW")
                .setCompleted(false)
                .build();

        orderRepo.add(order);

        for (OrderItem item : orderItems) {
            MenuItem menuItem = menuRepo.getById(item.getMenuItemId());
            int newQuantity = menuItem.getQuantity() - item.getQuantity();
            menuRepo.updateQuantity(item.getMenuItemId(), newQuantity);
        }
        return order;
    }

    public List<Order> getActiveOrders() throws SQLException {
        return orderRepo.findAllActive();
    }

    public void markOrderAsCompleted(int orderId) throws OrderNotFoundException, SQLException {
        Order order = orderRepo.getById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found in DB.");
        }
        orderRepo.updateStatus(orderId, true);
    }

    public void validateStock(int itemId, int requestedQty) throws MenuItemNotAvailableException, SQLException, InvalidQuantityException {
        MenuItem item = menuRepo.getById(itemId);
        if (requestedQty <= 0) {
            throw new InvalidQuantityException("Quantity must be at least 1.");
        }
        if (item == null) throw new MenuItemNotAvailableException("Item ID " + itemId + " not found.");
        if (item.getQuantity() < requestedQty) throw new InvalidQuantityException("Out of stock.");
    }

    public Order getOrderById(int orderId) throws SQLException {
        return orderRepo.getById(orderId);
    }
    public double getFinalPrice(double subtotal) {
        return subtotal + (subtotal * TaxConfig.getInstance().getTaxRate());
    }

}