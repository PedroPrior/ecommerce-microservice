package com.pedroprior.ecommercespring.controller;
;
import com.pedroprior.ecommercespring.dto.CartDto;
import com.pedroprior.ecommercespring.entities.*;


import com.pedroprior.ecommercespring.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<Void> addProductToCart(@RequestBody List<Product> products) {
        cartService.addProductToCart(products);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/get")
    public ResponseEntity<CartDto> getProductsInCart() {
        CartDto cartDto = cartService.getProductsInCart();
        if (cartDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/checkout")
    public ResponseEntity<Order> generateOrder() {
        try {
            Order order = cartService.generateOrder();
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/clear")
    public ResponseEntity<Void> delProductsInCart() {
        cartService.delProductsInCart();
        return ResponseEntity.ok().build();
    }



}
