package com.pedroprior.ecommercenotification.entities;

import java.time.LocalDate;

public class Payment {

    private Long id;
    private Order order;
    private String paymentType;
    private LocalDate paymentDate;
    private String paymentStatus;

}
