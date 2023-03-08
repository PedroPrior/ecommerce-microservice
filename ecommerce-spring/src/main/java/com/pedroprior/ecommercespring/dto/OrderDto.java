package com.pedroprior.ecommercespring.dto;

import com.pedroprior.ecommercespring.entities.Client;
import com.pedroprior.ecommercespring.entities.Delivery;
import com.pedroprior.ecommercespring.entities.OrderStatusModel;
import com.pedroprior.ecommercespring.entities.Product;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;

    private Client clientId;
    private List<Product> products;

    private BigDecimal totalValue;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    private OrderStatusModel orderStatus;

    private Delivery delivery;

    public OrderDto(Client clientId, List<Product> products, BigDecimal totalValue, Date orderDate, OrderStatusModel orderStatus) {
        this.clientId = clientId;
        this.products = products;
        this.totalValue = totalValue;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
