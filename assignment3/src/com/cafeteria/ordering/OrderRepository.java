package com.cafeteria.ordering;

import com.cafeteria.infrastructure.IDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) { this.db = db; }

    @Override
    public void add(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, status, completed, total_price ,order_type) VALUES (?, ?, ? ,? ,?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setString(2, order.getStatus());
            pstmt.setBoolean(3, order.isCompleted());
            pstmt.setDouble(4, order.getTotalPrice());
            pstmt.setString(5, order.getClass().getSimpleName());


            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void updateStatus(int orderId, boolean completed) throws SQLException {
        String sql = "UPDATE orders SET completed = ?, status = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, completed);
            pstmt.setString(2, completed ? "COMPLETED" : "ACTIVE");
            pstmt.setInt(3, orderId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Order> findAllActive() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE completed = false";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("status"),
                        rs.getBoolean("completed")
                ));
            }
        }
        return orders;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("status"),
                        rs.getBoolean("completed")
                ));
            }
        }
        return orders;
    }

    @Override
    public Order getById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getString("status"),
                            rs.getBoolean("completed")
                    );
                }
            }
        }
        return null;
    }
}
