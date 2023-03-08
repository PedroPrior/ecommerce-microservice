package com.pedroprior.ecommercespring.dto;

import com.pedroprior.ecommercespring.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;

    private List<Product> products;
}
