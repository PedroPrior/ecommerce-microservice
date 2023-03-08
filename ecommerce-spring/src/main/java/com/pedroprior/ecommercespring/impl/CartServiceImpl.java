package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.component.UserAuthenticated;
import com.pedroprior.ecommercespring.config.DateConfiguration;
import com.pedroprior.ecommercespring.dto.CartDto;
import com.pedroprior.ecommercespring.entities.*;
import com.pedroprior.ecommercespring.entities.enums.DeliveryStatus;
import com.pedroprior.ecommercespring.entities.enums.OrderStatus;
import com.pedroprior.ecommercespring.entities.enums.PaymentStatus;
import com.pedroprior.ecommercespring.entities.enums.PaymentType;
import com.pedroprior.ecommercespring.exceptions.ClientNotFoundException;
import com.pedroprior.ecommercespring.exceptions.OrderNotFoundException;
import com.pedroprior.ecommercespring.repositories.*;
import com.pedroprior.ecommercespring.services.CartService;
import com.pedroprior.ecommercespring.utils.RandomAddressStringGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final Cart cart = new Cart();
    private final ProductRepository productRepository;

    private final RandomAddressStringGenerator addressStringGenerator;
    private final DateConfiguration dateConfiguration;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryStatusModelRepository deliveryStatusModelRepository;
    private final AddressRepository addressRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentTypeModelRepository paymentTypeModelRepository;
    private final PaymentStatusModelRepository paymentStatusModelRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    private final UserAuthenticated userAuthenticated;

    public CartServiceImpl(ProductRepository productRepository,
                           RandomAddressStringGenerator addressStringGenerator, DateConfiguration dateConfiguration, DeliveryRepository deliveryRepository,
                           DeliveryStatusModelRepository deliveryStatusModelRepository,
                           AddressRepository addressRepository, PaymentRepository paymentRepository,
                           PaymentTypeModelRepository paymentTypeModelRepository,
                           PaymentStatusModelRepository paymentStatusModelRepository,
                           OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, UserAuthenticated userAuthenticated) {
        this.productRepository = productRepository;
        this.addressStringGenerator = addressStringGenerator;
        this.dateConfiguration = dateConfiguration;
        this.deliveryRepository = deliveryRepository;
        this.deliveryStatusModelRepository = deliveryStatusModelRepository;
        this.addressRepository = addressRepository;
        this.paymentRepository = paymentRepository;
        this.paymentTypeModelRepository = paymentTypeModelRepository;
        this.paymentStatusModelRepository = paymentStatusModelRepository;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.userAuthenticated = userAuthenticated;
    }

    @Override
    public void addProductToCart(List<Product> products) {

        cart.addProduct(products);
        cart.calculateSubtotal();

        log.info("Products has been added in the card");
    }

    @Override
    public CartDto getProductsInCart() {
        if (cart.getProducts().isEmpty()) {
            return null;
        }

        List<Product> products = cart.getProducts();
        BigDecimal subTotal = cart.getSubTotal();

        log.info("Subtotal of products in cart is {}", subTotal.toString());
        log.info("Quantity of products in cart is {}", 1);
        return new CartDto(products, subTotal);

    }

    @Override
    public void delProductsInCart() {
        if (!cart.getProducts().isEmpty()) {
            cart.setSubTotal(BigDecimal.valueOf(0));
            cart.getProductsList().clear();
        }

        log.info("Clean the card");
    }

    @Override
    public Order generateOrder() {

        Optional<Client> clientOptional = userAuthenticated.getClientAuthenticated();

        Client client;

        Optional<OrderStatusModel> orderStatusModelOptional = orderStatusRepository.findByOrderStatus(OrderStatus.PENDING);
        OrderStatusModel orderStatusModel;



        if(orderStatusModelOptional.isPresent()) {
            orderStatusModel = orderStatusModelOptional.get();
        } else {
            throw new OrderNotFoundException();
        }


        if(clientOptional.isPresent()) {
           client = clientOptional.get();
        } else {
            throw new ClientNotFoundException();
        }



        LocalDate orderDate = dateConfiguration.dateNow();
        LocalDate termDate = dateConfiguration.getDateAfterThreeDays();

        Payment clientPayment = generatePayment();

        Delivery delivery = generateDelivery();

        Order clientOrder = new Order(client, clientPayment, delivery, cart.getProducts(), cart.getSubTotal(), orderDate, termDate, orderStatusModel);

        if(cart.getProducts().isEmpty()) {
            throw new RuntimeException("Error: Card is empty");
        }

        orderRepository.save(clientOrder);

        cart.removeQuantity(productRepository);

        delProductsInCart();

        log.info("Order has been successfully generated");
        log.info("Payment id: {}", generatePayment().getId());
        log.info("Delivery id: {}", generateDelivery().getId());


        return clientOrder;
    }

    @Override
    public Delivery generateDelivery() {
        Optional<DeliveryStatusModel> deliveryStatusModel = deliveryStatusModelRepository.findByDeliveryStatus(DeliveryStatus.PENDING);

        DeliveryStatusModel deliveryStatus;

        if (deliveryStatusModel.isPresent()) {
            deliveryStatus = deliveryStatusModel.get();
        } else {
            throw new RuntimeException("Delivery status not found");
        }

        String trackingNumber = addressStringGenerator.generateRandomString();

        // Address of Logged User
        Optional<Address> addressOptional = addressRepository.findById(1L);
        Address address;

        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            throw new RuntimeException("Address status not found");
        }

        LocalDate testDate = dateConfiguration.getDateAfterThreeDays();

        Delivery delivery = new Delivery(deliveryStatus, testDate, trackingNumber, address);

        deliveryRepository.save(delivery);

        return delivery;
    }

    @Override
    public Payment generatePayment() {

        Optional<PaymentStatusModel> paymentStatusModelOptional = paymentStatusModelRepository.findByPaymentStatus(PaymentStatus.PENDING);
        Optional<PaymentTypeModel> paymentTypeModelOptional = paymentTypeModelRepository.findByPaymentType(PaymentType.BANK_SLIP);

        PaymentStatusModel paymentStatusModel;
        PaymentTypeModel paymentTypeModel;

        if (paymentStatusModelOptional.isPresent()) {
            paymentStatusModel = paymentStatusModelOptional.get();
        } else {
            throw new RuntimeException("PaymentStatusModel not found");
        }

        if (paymentTypeModelOptional.isPresent()) {
            paymentTypeModel = paymentTypeModelOptional.get();
        } else {
            throw new RuntimeException("PaymentTypeModel not found");
        }

        Payment payment = new Payment(paymentTypeModel, null, paymentStatusModel);

        payment = paymentRepository.save(payment);

        return payment;
    }


}
