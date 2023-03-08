package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByPayment(Payment payment);
    Order findByDelivery(Delivery delivery);


}
