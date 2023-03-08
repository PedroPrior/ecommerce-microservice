package com.pedroprior.ecommercespring.entities;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table( name = "tb_payment")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PaymentTypeModel paymentType;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    @OneToOne
    private PaymentStatusModel paymentStatus;

    public Payment(PaymentTypeModel paymentType, LocalDate paymentDate, PaymentStatusModel paymentStatus) {
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }
}
