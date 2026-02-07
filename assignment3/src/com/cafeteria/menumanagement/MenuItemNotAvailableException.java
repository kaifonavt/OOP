package com.cafeteria.menumanagement;

public class MenuItemNotAvailableException extends Exception {
    public MenuItemNotAvailableException(String message) {
        super(message);
    }
}
