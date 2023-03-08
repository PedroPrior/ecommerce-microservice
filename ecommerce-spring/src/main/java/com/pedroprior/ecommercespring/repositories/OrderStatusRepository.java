package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.OrderStatusModel;
import com.pedroprior.ecommercespring.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusModel, Long> {



    Optional<OrderStatusModel> findByOrderStatus(OrderStatus pending);

}
