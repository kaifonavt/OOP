package repositories;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    void add(T entity) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}
