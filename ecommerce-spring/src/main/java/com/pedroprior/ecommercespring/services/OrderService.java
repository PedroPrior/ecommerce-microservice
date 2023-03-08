package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.OrderDto;
import com.pedroprior.ecommercespring.entities.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(OrderDto orderDto);

    Order updateOrder(Long id, OrderDto orderDto) throws Exception;

    void removeOrder(Long id);

    Order getOrderById(Long id) throws Exception;

    List<Order> getAllOrders();
}
