package com.pedroprior.ecommercespring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tb_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer totalUnities;
    @Column(nullable = false)
    private BigDecimal price;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date validity;


    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Category category;

    public Product(String name, Integer quantity, BigDecimal price, Date validity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.validity = validity;
        this.description = description;
    }
    @Column(nullable = false)
    private String description;




    public Product() {

    }

    public void removeQuantity(int quantityToRemove) {
        this.totalUnities -= quantityToRemove;
    }
}
