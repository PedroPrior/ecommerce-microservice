package com.pedroprior.ecommercespring.exceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long id) {
        super("Payment not found with id " + id);
    }
}