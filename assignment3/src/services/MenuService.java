package services;

import entities.MenuItem;
import exceptions.InvalidInputException;
import exceptions.MenuItemNotAvailableException;
import repositories.IMenuItemRepository;
import util.Result;

import java.sql.SQLException;
import java.util.List;

public class MenuService {
    private IMenuItemRepository menuRepo;

    public MenuService(IMenuItemRepository menuItemRepository){
        this.menuRepo = menuItemRepository;
    }

    public Result<List<MenuItem>> getAllAvailableItems() {
        try {
            List<MenuItem> items = menuRepo.getAll().stream()
                    .filter(item -> item.getQuantity() > 0)
                    .toList();

            return Result.success(items);
        } catch (SQLException e) {
            // This is much better than just throwing the error
            return Result.error("Database error: " + e.getMessage());
        }
    }

    public MenuItem getMenuItemById(int id) throws SQLException, MenuItemNotAvailableException{
        MenuItem item = menuRepo.getById(id);
        if(item.getQuantity() <= 0 ) {
            throw new MenuItemNotAvailableException("Menu item '" + item.getName() + "' is not available");
        }
        return item;
    }
    public void addMenuItem(MenuItem item) throws InvalidInputException, SQLException {
        if (item.getName() == null || item.getName().trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty!");
        }
        if (item.getPrice() < 0) {
            throw new InvalidInputException("Price cannot be negative!");
        }
        if (item.getQuantity() < 0) {
            throw new InvalidInputException("Quantity cannot be negative!");
        }
        menuRepo.add(item);
    }

}