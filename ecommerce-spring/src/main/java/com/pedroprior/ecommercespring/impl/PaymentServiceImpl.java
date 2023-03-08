package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.config.DateConfiguration;
import com.pedroprior.ecommercespring.dto.PaymentDto;
import com.pedroprior.ecommercespring.entities.Order;
import com.pedroprior.ecommercespring.entities.OrderStatusModel;
import com.pedroprior.ecommercespring.entities.Payment;
import com.pedroprior.ecommercespring.entities.PaymentStatusModel;
import com.pedroprior.ecommercespring.entities.enums.OrderStatus;
import com.pedroprior.ecommercespring.entities.enums.PaymentStatus;
import com.pedroprior.ecommercespring.exceptions.PaymentNotFoundException;
import com.pedroprior.ecommercespring.repositories.OrderRepository;
import com.pedroprior.ecommercespring.repositories.OrderStatusRepository;
import com.pedroprior.ecommercespring.repositories.PaymentRepository;
import com.pedroprior.ecommercespring.repositories.PaymentStatusModelRepository;
import com.pedroprior.ecommercespring.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStatusModelRepository paymentStatusModelRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final DateConfiguration dateConfiguration;
    private final ModelMapper modelMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStatusModelRepository paymentStatusModelRepository, OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, DateConfiguration dateConfiguration, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentStatusModelRepository = paymentStatusModelRepository;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.dateConfiguration = dateConfiguration;
        this.modelMapper = modelMapper;
    }


    @Override
    public Payment addPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        log.info("POST - Add payment");
        return paymentRepository.save(payment);
    }

    @Override
    public Payment alterPayment(Long id, PaymentDto paymentDto)  {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isEmpty()) {
            throw new PaymentNotFoundException(id);
        }

        Payment payment = optionalPayment.get();

        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentType(paymentDto.getPaymentType());

        modelMapper.map(paymentDto, payment);

        log.info("PUT - alter payment id: {}", id);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment processPayment(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException(id);
        }

        Payment payment = paymentOptional.get();

        findPaymentStatus(payment, paymentStatusModelRepository, dateConfiguration, orderRepository, orderStatusRepository);

        log.info("PUT - process payment id: {}", id);
        return paymentRepository.save(payment);
    }

    public static void findPaymentStatus(Payment payment, PaymentStatusModelRepository paymentStatusModelRepository, DateConfiguration dateConfiguration, OrderRepository orderRepository, OrderStatusRepository orderStatusRepository) {
        Optional<PaymentStatusModel> paymentStatus = paymentStatusModelRepository.findByPaymentStatus(PaymentStatus.PAID);

        payment.setPaymentStatus(paymentStatus.get());
        payment.setPaymentDate(dateConfiguration.dateNow());

        Order order = orderRepository.findByPayment(payment);

        if (order != null) {
            Optional<OrderStatusModel> orderStatus = orderStatusRepository.findByOrderStatus(OrderStatus.PAID);
            order.setOrderStatus(orderStatus.get());
            orderRepository.save(order);
        }
    }


    @Override
    public void removePayment(Long id) {
        log.info("DELETE - delete payment id: {}", id);
        paymentRepository.deleteById(id);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        log.info("GET - payment id: {}", id);
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        log.info("GET - All payments");
        return paymentRepository.findAll();
    }
}
