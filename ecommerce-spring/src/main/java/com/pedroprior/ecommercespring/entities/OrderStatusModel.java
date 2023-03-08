package com.pedroprior.ecommercespring.entities;

import com.pedroprior.ecommercespring.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_order_status")
@Data
public class OrderStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;


}
