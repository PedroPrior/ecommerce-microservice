package com.pedroprior.ecommercespring.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super("Client not found");
    }
}