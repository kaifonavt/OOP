package repositories;

import entities.Order;
import java.sql.SQLException;
import java.util.List;

public interface IOrderRepository extends IRepository {
    void create(Order order) throws SQLException;
    void updateStatus(int orderId, boolean completed) throws SQLException;
    List<Order> findAllActive() throws SQLException;
    Order findById(int id) throws SQLException;
}
