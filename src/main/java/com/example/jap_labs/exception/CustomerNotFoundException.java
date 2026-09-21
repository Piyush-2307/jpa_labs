package com.example.jap_labs.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer not found");
    }
    public CustomerNotFoundException(String email) {
        super("Customer not found");
    }

}
