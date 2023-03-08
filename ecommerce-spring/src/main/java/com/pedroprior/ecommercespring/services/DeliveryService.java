package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.DeliveryDto;
import com.pedroprior.ecommercespring.entities.Delivery;

import java.util.List;

public interface DeliveryService {

    Delivery addDelivery(DeliveryDto deliveryDto);
    Delivery updateDelivery(Long id, DeliveryDto deliveryDto) throws Exception;
    Delivery processDelivery(Long id) throws Exception;
    void removeDelivery(Long id);
    Delivery getDeliveryById(Long id) throws Exception;
    List<Delivery> getAllDeliveries();
}
