package com.pedroprior.ecommercespring.entities;


import com.pedroprior.ecommercespring.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_payment_status")
@Data
public class PaymentStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
