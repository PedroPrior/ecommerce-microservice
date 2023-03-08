package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.PaymentStatusModel;
import com.pedroprior.ecommercespring.entities.PaymentTypeModel;
import com.pedroprior.ecommercespring.entities.enums.PaymentStatus;
import com.pedroprior.ecommercespring.entities.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentStatusModelRepository extends JpaRepository<PaymentStatusModel, Long> {

    Optional<PaymentStatusModel> findByPaymentStatus(PaymentStatus pending);
}
