package com.pedroprior.ecommercespring.dto;

import com.pedroprior.ecommercespring.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CartDto {
    private List<Product> products;
    private BigDecimal subTotal;


}
