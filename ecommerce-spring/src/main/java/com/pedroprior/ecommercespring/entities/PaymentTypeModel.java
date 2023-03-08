package com.pedroprior.ecommercespring.entities;



import com.pedroprior.ecommercespring.entities.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_payment_type")
@Data
public class PaymentTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

}
