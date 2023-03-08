package com.pedroprior.ecommercespring.controller;



import com.pedroprior.ecommercespring.dto.DeliveryDto;
import com.pedroprior.ecommercespring.entities.*;
import com.pedroprior.ecommercespring.services.DeliveryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/deliveries")
@Slf4j
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<Delivery> addDelivery(@RequestBody DeliveryDto deliveryDto) {
        Delivery delivery = deliveryService.addDelivery(deliveryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(delivery);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Delivery> alterDelivery(@PathVariable Long id, @RequestBody DeliveryDto deliveryDto) throws Exception {
        Delivery delivery = deliveryService.updateDelivery(id, deliveryDto);
        return ResponseEntity.status(HttpStatus.OK).body(delivery);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/confirm/{id}")
    public ResponseEntity<Delivery> processDelivery(@PathVariable Long id) throws Exception {
        Delivery delivery = deliveryService.processDelivery(id);
        return ResponseEntity.ok().body(delivery);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeDelivery(@PathVariable Long id) {
        deliveryService.removeDelivery(id);
        log.info("DELETE - Delivery id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body("Delivery was deleted.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) throws Exception {
        Delivery delivery = deliveryService.getDeliveryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(delivery);
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        return ResponseEntity.status(HttpStatus.OK).body(deliveries);
    }
}
