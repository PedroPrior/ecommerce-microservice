package com.pedroprior.ecommercespring.entities.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public enum OrderStatus {

    PENDING,
    SHIPPED,
    DELIVERED,
    PAID

}
