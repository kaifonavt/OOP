package com.cafeteria.ordering;

import com.cafeteria.infrastructure.IRepository;

import java.sql.SQLException;
import java.util.List;

public interface IOrderRepository extends IRepository<Order> {
    void add(Order order) throws SQLException;
    void updateStatus(int orderId, boolean completed) throws SQLException;
    List<Order> findAllActive() throws SQLException;
    Order getById(int id) throws SQLException;
}
