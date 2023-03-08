package com.pedroprior.ecommercenotification.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Long id;

    private String name;

    private Integer quantity;

    private BigDecimal price;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date validity;

    private String description;
    private String categoryName;
}
