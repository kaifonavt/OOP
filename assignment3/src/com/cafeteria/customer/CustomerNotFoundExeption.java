package com.cafeteria.customer;

public class CustomerNotFoundExeption extends RuntimeException {
    public CustomerNotFoundExeption(String message) {
        super(message);
    }
}
