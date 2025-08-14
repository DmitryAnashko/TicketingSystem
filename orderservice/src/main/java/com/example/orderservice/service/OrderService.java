package com.example.orderservice.service;


import com.example.shared.event.BookingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @KafkaListener(topics = "bookingEvent", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("Order Event Received {}", bookingEvent);

        //create order object for a database


        //update inventory
    }
}
