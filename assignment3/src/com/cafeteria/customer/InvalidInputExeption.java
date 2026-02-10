package com.cafeteria.customer;

public class InvalidInputExeption extends RuntimeException {
    public InvalidInputExeption(String message) {
        super(message);
    }
}
