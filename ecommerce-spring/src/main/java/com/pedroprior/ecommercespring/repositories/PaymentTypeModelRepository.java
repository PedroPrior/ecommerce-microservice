package com.pedroprior.ecommercespring.repositories;


import com.pedroprior.ecommercespring.entities.PaymentTypeModel;
import com.pedroprior.ecommercespring.entities.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTypeModelRepository extends JpaRepository<PaymentTypeModel, Long> {

    Optional<PaymentTypeModel> findByPaymentType(PaymentType bankSlip);
}
