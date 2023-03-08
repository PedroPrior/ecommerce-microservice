package com.pedroprior.ecommercespring.impl;


import com.pedroprior.ecommercespring.config.DateConfiguration;
import com.pedroprior.ecommercespring.dto.DeliveryDto;
import com.pedroprior.ecommercespring.entities.Delivery;
import com.pedroprior.ecommercespring.entities.DeliveryStatusModel;
import com.pedroprior.ecommercespring.entities.Order;
import com.pedroprior.ecommercespring.entities.OrderStatusModel;
import com.pedroprior.ecommercespring.entities.enums.DeliveryStatus;
import com.pedroprior.ecommercespring.entities.enums.OrderStatus;
import com.pedroprior.ecommercespring.repositories.DeliveryRepository;
import com.pedroprior.ecommercespring.repositories.DeliveryStatusModelRepository;
import com.pedroprior.ecommercespring.repositories.OrderRepository;
import com.pedroprior.ecommercespring.repositories.OrderStatusRepository;
import com.pedroprior.ecommercespring.services.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryStatusModelRepository deliveryStatusModelRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ModelMapper modelMapper;
    private final DateConfiguration dateConfiguration;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryStatusModelRepository deliveryStatusModelRepository, OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, ModelMapper modelMapper, DateConfiguration dateConfiguration) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryStatusModelRepository = deliveryStatusModelRepository;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.modelMapper = modelMapper;
        this.dateConfiguration = dateConfiguration;
    }

    @Override
    public Delivery addDelivery(DeliveryDto deliveryDto) {

        Delivery delivery = modelMapper.map(deliveryDto, Delivery.class);
        log.info("POST - New delivery");
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDelivery(Long id, DeliveryDto deliveryDto) throws Exception {

        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);

        if(optionalDelivery.isEmpty()) {
            throw new Exception("Delivery not found with id: " + id);
        }

        Delivery delivery = optionalDelivery.get();

        delivery.setDeliveryDate(deliveryDto.getDeliveryDate());
        delivery.setDeliveryStatus(deliveryDto.getDeliveryStatus());
        delivery.setAddress(deliveryDto.getAddress());

        modelMapper.map(deliveryDto, delivery);

        log.info("PUT - Delivery id: {}", id);
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery processDelivery(Long id) throws Exception {
        
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
        Delivery delivery = deliveryOptional.orElseThrow(() -> new Exception("Delivery not found with id: " + id));

        Optional<DeliveryStatusModel> deliveryStatusModel = deliveryStatusModelRepository.findByDeliveryStatus(DeliveryStatus.DELIVERED);
        delivery.setDeliveryStatus(deliveryStatusModel.get());
        delivery.setDeliveryDate(dateConfiguration.dateNow());

        Order order = orderRepository.findByDelivery(delivery);

        if (order != null) {
            Optional<OrderStatusModel> orderStatus = orderStatusRepository.findByOrderStatus(OrderStatus.DELIVERED);
            order.setOrderStatus(orderStatus.get());
            orderRepository.save(order);
        }

        deliveryRepository.save(delivery);
        log.info("PUT - Delivery id: {}", id);
        return delivery;
    }

    @Override
    public void removeDelivery(Long id) {

        deliveryRepository.deleteById(id);
        log.info("DELETE - Delivery id: {}", id);

    }

    @Override
    public Delivery getDeliveryById(Long id) throws Exception {

        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        log.info("GET - Delivery id: {}", id);
        return optionalDelivery.orElseThrow(() -> new Exception("Delivery not found with id: " + id));
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
