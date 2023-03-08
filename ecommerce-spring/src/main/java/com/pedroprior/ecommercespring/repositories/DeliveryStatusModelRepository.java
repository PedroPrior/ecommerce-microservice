package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.DeliveryStatusModel;
import com.pedroprior.ecommercespring.entities.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryStatusModelRepository extends JpaRepository<DeliveryStatusModel, Long> {

    Optional<DeliveryStatusModel> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
