package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.PaymentDto;
import com.pedroprior.ecommercespring.entities.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment addPayment(PaymentDto paymentDto);
    Payment alterPayment(Long id, PaymentDto paymentDto);
    Payment processPayment(Long id);
    void removePayment(Long id);
    Optional<Payment> getPaymentById(Long id);
    List<Payment> getAllPayments();

}
