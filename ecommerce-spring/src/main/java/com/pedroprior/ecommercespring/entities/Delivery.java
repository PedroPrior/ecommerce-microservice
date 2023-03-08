package com.pedroprior.ecommercespring.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_delivery")
@Data
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private DeliveryStatusModel deliveryStatus;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Column(nullable = false, unique = true)
    private String trackingNumber;

    @OneToOne
    private Address address;

    public Delivery(DeliveryStatusModel deliveryStatus, LocalDate deliveryDate, String trackingNumber, Address address) {
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.trackingNumber = trackingNumber;
        this.address = address;
    }

    public Delivery() {

    }


}
