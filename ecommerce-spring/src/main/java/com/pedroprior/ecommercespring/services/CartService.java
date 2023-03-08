package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.CartDto;
import com.pedroprior.ecommercespring.dto.DeliveryDto;
import com.pedroprior.ecommercespring.entities.Delivery;
import com.pedroprior.ecommercespring.entities.Order;
import com.pedroprior.ecommercespring.entities.Payment;
import com.pedroprior.ecommercespring.entities.Product;

import java.util.List;

public interface CartService {

    void addProductToCart(List<Product> products);
    CartDto getProductsInCart();
    void delProductsInCart();
    Order generateOrder();

    Delivery generateDelivery();

    Payment generatePayment();



}
