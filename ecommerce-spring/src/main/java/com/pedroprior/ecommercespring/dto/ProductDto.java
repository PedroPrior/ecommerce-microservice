package com.pedroprior.ecommercespring.dto;



import com.pedroprior.ecommercespring.entities.Product;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private Integer quantity;

    private BigDecimal price;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date validity;

    private String description;
    private String categoryName;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.validity = product.getValidity();
        this.categoryName = product.getCategory().getName();

    }
}
