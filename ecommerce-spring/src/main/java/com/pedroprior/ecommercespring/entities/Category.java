package com.pedroprior.ecommercespring.entities;


import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_category")
@Data
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public Category() {

    }
}
