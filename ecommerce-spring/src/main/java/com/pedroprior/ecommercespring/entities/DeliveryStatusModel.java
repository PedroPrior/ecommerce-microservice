package com.pedroprior.ecommercespring.entities;


import com.pedroprior.ecommercespring.entities.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_delivery_status")
@Data
public class DeliveryStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public DeliveryStatusModel(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public DeliveryStatusModel() {

    }
}
