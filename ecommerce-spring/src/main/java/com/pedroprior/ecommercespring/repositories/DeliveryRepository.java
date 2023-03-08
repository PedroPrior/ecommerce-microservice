package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
