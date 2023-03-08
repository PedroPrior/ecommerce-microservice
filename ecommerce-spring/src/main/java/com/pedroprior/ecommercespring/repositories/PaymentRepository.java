package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.Order;
import com.pedroprior.ecommercespring.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

 }
