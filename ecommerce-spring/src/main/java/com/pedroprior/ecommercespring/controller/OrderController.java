package com.pedroprior.ecommercespring.controller;

import com.pedroprior.ecommercespring.dto.OrderDto;
import com.pedroprior.ecommercespring.entities.Order;

import com.pedroprior.ecommercespring.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.addOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) throws Exception {
        Order order = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeOrder(@PathVariable Long id) {
        orderService.removeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws Exception {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }



}
