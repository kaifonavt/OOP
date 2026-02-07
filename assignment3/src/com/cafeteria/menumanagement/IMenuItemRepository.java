package com.cafeteria.menumanagement;

import com.cafeteria.infrastructure.IRepository;

import java.sql.SQLException;
import java.util.List;

public interface IMenuItemRepository extends IRepository<MenuItem> {
    void add(MenuItem item) throws  SQLException;
    List<MenuItem> getAll() throws SQLException;
    MenuItem getById(int id) throws  SQLException;
    void updateQuantity(int id, int newQuantity) throws SQLException;
}

