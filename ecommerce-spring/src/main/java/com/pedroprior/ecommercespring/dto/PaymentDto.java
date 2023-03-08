package com.pedroprior.ecommercespring.dto;


import com.pedroprior.ecommercespring.entities.Order;
import com.pedroprior.ecommercespring.entities.PaymentStatusModel;
import com.pedroprior.ecommercespring.entities.PaymentTypeModel;
import jakarta.persistence.OneToOne;
import lombok.Data;


import java.time.LocalDate;


@Data
public class PaymentDto {
    private Long id;

    @OneToOne
    private Order order;
    private PaymentTypeModel paymentType;
    private LocalDate paymentDate;
    private PaymentStatusModel paymentStatus;
}
