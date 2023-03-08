package com.pedroprior.ecommercenotification.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;

    private Client clientId;
    private List<Product> products;

    private BigDecimal totalValue;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    private String orderStatus;

    private Delivery delivery;

}
