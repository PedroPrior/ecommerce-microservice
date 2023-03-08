package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.dto.OrderDto;
import com.pedroprior.ecommercespring.entities.Order;
import com.pedroprior.ecommercespring.repositories.OrderRepository;
import com.pedroprior.ecommercespring.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Order addOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        log.info("POST - New order");
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, OrderDto orderDto) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) {
            throw new Exception("Order not found with id: " + id);
        }

        Order order = optionalOrder.get();

        order.setOrderStatus(order.getOrderStatus());

        modelMapper.map(orderDto, order);

        orderRepository.save(order);

        log.info("PUT - Order id: {}", order.getId().toString());
        return order;
    }


    @Override
    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
        log.info("DELETE - Order id: {}", id);
    }

    @Override
    public Order getOrderById(Long id) throws Exception {

        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new Exception("Order not found with id: " + id);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
