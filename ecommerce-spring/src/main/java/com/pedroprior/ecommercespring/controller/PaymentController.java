package com.pedroprior.ecommercespring.controller;

import com.pedroprior.ecommercespring.dto.PaymentDto;
import com.pedroprior.ecommercespring.entities.Payment;

import com.pedroprior.ecommercespring.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/payments")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<Payment> addPayment(@RequestBody PaymentDto paymentDto) {
        Payment payment = paymentService.addPayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Payment> alterPayment(@PathVariable Long id, @RequestBody PaymentDto paymentDto) {
        Payment payment = paymentService.alterPayment(id, paymentDto);
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/confirm/{id}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long id) {
        Payment payment = paymentService.processPayment(id);
        return ResponseEntity.ok().body(payment);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePayment(@PathVariable Long id) {
        paymentService.removePayment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Payment was deleted.");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Payment>> getPaymentById(@PathVariable Long id) {
        Optional<Payment> getPayment = paymentService.getPaymentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getPayment);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> paymentList = paymentService.getAllPayments();
        return ResponseEntity.status(HttpStatus.OK).body(paymentList);
    }

}
