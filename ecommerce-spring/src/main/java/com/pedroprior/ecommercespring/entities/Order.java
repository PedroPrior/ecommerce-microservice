package com.pedroprior.ecommercespring.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    @SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence", allocationSize = 1)
    private Long id;

    @OneToOne
    private Client clientId;

    @OneToOne
    private Payment payment;

    @OneToOne
    private Delivery delivery;

    @JsonIgnoreProperties("orders")
    @ManyToMany
    @JoinTable(
            name = "tb_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
    private BigDecimal totalValue;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate termDate;
    @OneToOne
    private OrderStatusModel orderStatus;



    public Order(Client clientId, Payment payment, Delivery delivery, List<Product> products, BigDecimal totalValue, LocalDate orderDate, LocalDate termDate, OrderStatusModel orderStatus) {
        this.clientId = clientId;
        this.payment = payment;
        this.delivery = delivery;
        this.products = products;
        this.totalValue = totalValue;
        this.orderDate = orderDate;
        this.termDate = termDate;
        this.orderStatus = orderStatus;
    }

    public Order() {

    }



}
