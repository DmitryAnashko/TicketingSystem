package com.example.orderservice.service;


import com.example.orderservice.client.InventoryServiceClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.shared.event.BookingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }
    @KafkaListener(topics = "bookingEvent", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("Order Event Received {}", bookingEvent);

        //create order object for a database
        Order order = createOrder(bookingEvent);

        orderRepository.saveAndFlush(order);

        //update inventory creating client for order service
        inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
        log.info("Updated inventory for event {}, tickets reduced by {}", order.getEventId(), order.getTicketCount());

    }


    private Order createOrder(BookingEvent bookingEvent){
        return  Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}
