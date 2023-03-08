package com.pedroprior.ecommercespring.dto;

import com.pedroprior.ecommercespring.entities.Address;
import com.pedroprior.ecommercespring.entities.DeliveryStatusModel;


import lombok.Data;

import java.time.LocalDate;
@Data
public class DeliveryDto {
    private DeliveryStatusModel deliveryStatus;

    private LocalDate deliveryDate;

    private String trackingNumber;

    private Address address;

}
