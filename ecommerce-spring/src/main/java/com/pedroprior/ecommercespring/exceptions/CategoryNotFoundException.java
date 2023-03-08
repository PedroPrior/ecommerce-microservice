package com.pedroprior.ecommercespring.exceptions;

import com.pedroprior.ecommercespring.entities.Client;

import java.util.Optional;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category not found" + id);
    }
}